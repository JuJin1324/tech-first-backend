package com.techfirst.backend.honestfactory.infrastructure.persistence;

import com.techfirst.backend.honestfactory.domain.Member;
import com.techfirst.backend.honestfactory.domain.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MockMemberRepository implements MemberRepository {

    private final Map<Long, Member> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Member save(Member member) {
        Long id = member.id();
        if (id == null) {
            id = sequence.getAndIncrement();
        }
        
        // Re-create member with ID (simulating DB insertion)
        Member savedMember = new Member(id, member.name(), member.age());
        storage.put(id, savedMember);
        return savedMember;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
