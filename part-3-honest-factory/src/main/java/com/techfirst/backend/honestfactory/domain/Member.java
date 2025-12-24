package com.techfirst.backend.honestfactory.domain;

import io.vavr.control.Either;

/**
 * Member 도메인 엔티티 (Record 타입으로 불변성 강조).
 *
 * <p>
 *   private 생성자와 정적 팩토리 메서드를 사용하여 불변 객체의 유효성 검증을 강제합니다.
 *   예외를 던지는 대신(부수 효과), Either 타입을 반환하여 함수의 정직성을 높입니다.
 *   (Either.Left: ErrorCode 반환, Either.Right: Member 객체 반환)
 * </p>
 */
public record Member(Long id, String name, int age) {

    // 새로운 Member 생성 시 호출되는 컴팩트 생성자 (ID는 null)
    public Member(String name, int age) {
        this(null, name, age);
    }

    /**
     * "정직한 생성자(Honest Constructor)" 패턴을 구현한 정적 팩토리 메서드.
     * <p>
     *   입력을 검증하고, 실패 시 Either.Left(ErrorCode)를,
     *   성공 시 Either.Right(Member)를 반환합니다.
     * </p>
     *
     * @param name 멤버 이름
     * @param age 멤버 나이
     * @return 유효성 검증 결과 (ErrorCode 또는 Member 객체)
     */
    public static Either<ErrorCode, Member> create(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            return Either.left(ErrorCode.INVALID_NAME);
        }
        if (age < 0 || age > 150) {
            return Either.left(ErrorCode.AGE_LIMIT_EXCEEDED);
        }
        return Either.right(new Member(name, age));
    }
}
