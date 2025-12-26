package com.techfirst.backend.pragmaticfp.service;

import com.techfirst.backend.pragmaticfp.domain.Member;

public record MemberDto(Long id, String name, int age) {
    public static MemberDto from(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getAge());
    }
}
