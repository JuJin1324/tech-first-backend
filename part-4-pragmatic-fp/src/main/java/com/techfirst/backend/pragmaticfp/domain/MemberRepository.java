package com.techfirst.backend.pragmaticfp.domain;

import io.vavr.control.Option;

public interface MemberRepository {
    Option<Member> findById(Long id);
    Member save(Member member);
}
