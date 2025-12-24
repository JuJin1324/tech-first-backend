package com.techfirst.backend.honestfactory.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_NAME("이름은 null이거나 비어 있을 수 없습니다."),
    AGE_LIMIT_EXCEEDED("나이는 0세에서 150세 사이여야 합니다."),
    MEMBER_NOT_FOUND("해당 멤버를 찾을 수 없습니다."),
    DB_ERROR("데이터베이스 오류가 발생했습니다.");

    private final String message;
}
