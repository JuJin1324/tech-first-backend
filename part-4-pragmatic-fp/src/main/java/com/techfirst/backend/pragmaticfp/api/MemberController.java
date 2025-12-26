package com.techfirst.backend.pragmaticfp.api;

import com.techfirst.backend.pragmaticfp.domain.ErrorCode;
import com.techfirst.backend.pragmaticfp.service.MemberDto;
import com.techfirst.backend.pragmaticfp.service.MemberService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/{id}/name")
    public ResponseEntity<?> changeName(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newName = body.get("name");
        
        Either<ErrorCode, MemberDto> result = memberService.changeName(id, newName);

        if (result.isLeft()) {
            return ResponseEntity.badRequest().body(Map.of("error", result.getLeft().message()));
        } else {
            // 변경된 회원 정보를 그대로 반환합니다.
            return ResponseEntity.ok(result.get());
        }
    }
}