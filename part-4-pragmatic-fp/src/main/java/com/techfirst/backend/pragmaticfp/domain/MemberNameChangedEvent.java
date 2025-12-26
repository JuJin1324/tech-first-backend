package com.techfirst.backend.pragmaticfp.domain;

public record MemberNameChangedEvent(Long memberId, String newName) {
}
