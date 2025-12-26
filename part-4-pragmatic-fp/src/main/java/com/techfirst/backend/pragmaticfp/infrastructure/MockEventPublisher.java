package com.techfirst.backend.pragmaticfp.infrastructure;

import com.techfirst.backend.pragmaticfp.domain.MemberNameChangedEvent;
import com.techfirst.backend.pragmaticfp.service.EventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MockEventPublisher implements EventPublisher {
    @Override
    public void publish(MemberNameChangedEvent event) {
        System.out.println("[이벤트 발행] 회원 ID " + event.memberId() + "의 이름이 " + event.newName() + "(으)로 변경되었습니다.");
    }
}
