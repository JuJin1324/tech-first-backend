package com.techfirst.backend.pragmaticfp.infrastructure;

import com.techfirst.backend.pragmaticfp.domain.Member;
import com.techfirst.backend.pragmaticfp.domain.MemberRepository;

import io.vavr.control.Option;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MockMemberRepository implements MemberRepository {

    // 동시성을 고려한 인메모리 저장소 및 ID 시퀀스
    private final Map<Long, Member> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public MockMemberRepository() {
        // 테스트 편의를 위해 초기 데이터 1건을 미리 적재합니다.
        storage.put(1L, new Member(1L, "Alice", 30));
        sequence.incrementAndGet(); // ID 1 사용 처리
    }

    @Override
    public Member save(Member member) {
        Long id = member.getId();
        if (id == null) {
            id = sequence.getAndIncrement();
        }

        // DB에서 ID가 생성되는 상황을 모사하여, ID가 부여된 새로운 객체를 저장합니다.
        // 식별자(Identity)가 부여된 엔티티를 관리하기 위함입니다.
        Member savedMember = new Member(id, member.getName(), member.getAge());
        storage.put(id, savedMember);
        return savedMember;
    }

    @Override
    public Option<Member> findById(Long id) {
        // Vavr의 Option.of는 null일 경우 자동으로 Option.None을 반환합니다.
        return Option.of(storage.get(id));
    }
}
