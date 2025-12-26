package com.techfirst.backend.pragmaticfp.domain;

import io.vavr.control.Either;
import lombok.Getter;

@Getter
public class Member {
    private final Long id;
    private String name;
    private final int age;

    // 데이터베이스 영속성 계층이나 초기 생성 시 사용되는 생성자
    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // 정적 팩토리 메서드 (Part 3의 Honest Constructor 패턴)
    public static Either<ErrorCode, Member> create(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            return Either.left(new ErrorCode("이름은 비어있을 수 없습니다."));
        }
        if (age < 0 || age > 150) {
            return Either.left(new ErrorCode("유효하지 않은 나이입니다."));
        }
        // 새 인스턴스 생성 시 ID는 null (DB에서 생성 예정)
        return Either.right(new Member(null, name, age));
    }

    /**
     * 실용주의적 FP 접근 방식:
     * 1. 검증 (FP - 순수함): 입력값의 유효성을 검증하여 오류를 값으로 다룸.
     * 2. 변경 (OOP - 부수효과): "상태 머신"으로서 자신의 필드를 직접 변경.
     * 3. 결과 반환 (EDA/FP - 값): 어떤 사건이 일어났는지 이벤트를 반환.
     */
    public Either<ErrorCode, MemberNameChangedEvent> changeName(String newName) {
        // 1. [FP] 엄격한 검증 (Validation)
        if (newName == null || newName.length() < 2) {
            return Either.left(ErrorCode.INVALID_NAME);
        }

        // 2. [OOP] 상태 변경 (State Mutation)
        this.name = newName;

        // 3. [EDA/FP] 결과 보고 (Return Event)
        return Either.right(new MemberNameChangedEvent(this.id, newName));
    }
}
