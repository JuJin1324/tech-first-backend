package com.techfirst.backend.honestfactory.api;

import com.techfirst.backend.honestfactory.domain.ErrorCode;
import com.techfirst.backend.honestfactory.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 새로운 멤버를 등록하는 API
    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody RegisterMemberRequest request) {
        return memberService
                .register(request.name(), request.age())
                .fold(this::mapErrorToResponse, ResponseEntity::ok);
    }

    // ID를 통해 멤버 정보를 조회하는 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        return memberService
                .getMember(id)
                .fold(this::mapErrorToResponse, ResponseEntity::ok);
    }

    // ErrorCode에 따라 적절한 HTTP 응답 및 메시지를 반환
    private ResponseEntity<String> mapErrorToResponse(ErrorCode error) {
        if (error == ErrorCode.DB_ERROR) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage());
        }
        if (error == ErrorCode.MEMBER_NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
        return ResponseEntity.badRequest().body(error.getMessage());
    }

    public record RegisterMemberRequest(String name, int age) {}
}
