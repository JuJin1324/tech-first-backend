package com.techfirst.backend.pragmaticfp.domain;

public record ErrorCode(String message) {
    public static final ErrorCode INVALID_NAME = new ErrorCode("이름은 최소 2글자 이상이어야 합니다.");
    public static final ErrorCode MEMBER_NOT_FOUND = new ErrorCode("회원을 찾을 수 없습니다.");
}
