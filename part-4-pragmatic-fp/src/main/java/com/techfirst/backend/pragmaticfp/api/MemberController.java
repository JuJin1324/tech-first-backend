package com.techfirst.backend.pragmaticfp.api;

import com.techfirst.backend.pragmaticfp.service.MemberService;

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
    public ResponseEntity<?> changeName(
            @PathVariable Long id, @RequestBody Map<String, String> body) {

        return memberService
                .changeName(id, body.get("name"))
                .fold(
                        error -> ResponseEntity.badRequest().body(Map.of("error", error.message())),
                        ResponseEntity::ok);
    }
}
