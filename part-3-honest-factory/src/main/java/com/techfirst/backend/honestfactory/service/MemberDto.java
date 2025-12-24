package com.techfirst.backend.honestfactory.service;

import com.techfirst.backend.honestfactory.domain.Member;

public record MemberDto(Long id, String name, int age) {

    public static MemberDto from(Member member) {
        return new MemberDto(member.id(), member.name(), member.age());
    }
}
