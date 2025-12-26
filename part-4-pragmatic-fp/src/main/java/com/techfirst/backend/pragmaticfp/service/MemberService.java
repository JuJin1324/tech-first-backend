package com.techfirst.backend.pragmaticfp.service;

import com.techfirst.backend.pragmaticfp.domain.ErrorCode;
import com.techfirst.backend.pragmaticfp.domain.MemberRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public Either<ErrorCode, MemberDto> changeName(Long id, String newName) {
        return memberRepository.findById(id)
                .toEither(() -> ErrorCode.MEMBER_NOT_FOUND)
                .flatMap(member -> member.changeName(newName) // 1. 엔티티 내부 상태 변경
                        .peek(event -> memberRepository.save(member)) // 2. 저장 (부수효과)
                        .peek(eventPublisher::publish)                // 3. 결과 이벤트 발행
                        // 4. 변경된 엔티티 상태를 DTO로 변환하여 반환
                        .map(event -> MemberDto.from(member))
                );
    }
}
