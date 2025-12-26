package com.techfirst.backend.honestfactory.service;

import com.techfirst.backend.honestfactory.domain.ErrorCode;
import com.techfirst.backend.honestfactory.domain.Member;
import com.techfirst.backend.honestfactory.domain.MemberRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 새로운 멤버를 등록합니다.
     * <p>
     *   Vavr의 Either를 사용하여 예외 없이 성공/실패 흐름을 처리합니다.
     *   (Railway Oriented Programming)
     * </p>
     *
     * @param name 멤버 이름
     * @param age  멤버 나이
     * @return 등록 결과 (ErrorCode 또는 MemberDto)
     */
    public Either<ErrorCode, MemberDto> register(String name, int age) {
        return Member.create(name, age)
                .flatMap(this::saveToRepository)
                .map(MemberDto::from);
    }

    /**
     * ID를 통해 멤버 정보를 조회합니다.
     * <p>
     *   조회 실패 시 예외 대신 `Either.Left(MEMBER_NOT_FOUND)`를 반환합니다.
     * </p>
     *
     * @param id 멤버 ID
     * @return 조회 결과 (ErrorCode 또는 MemberDto)
     */
    public Either<ErrorCode, MemberDto> getMember(Long id) {
        return Option.ofOptional(memberRepository.findById(id))
                .toEither(ErrorCode.MEMBER_NOT_FOUND)
                .map(MemberDto::from);
    }

    // Repository 저장 로직을 Either로 감싸서 예외를 ErrorCode로 변환
    private Either<ErrorCode, Member> saveToRepository(Member member) {
        return Try.of(() -> memberRepository.save(member))
                .toEither(ErrorCode.DB_ERROR);
    }
}
