package com.techfirst.backend.pragmaticfp.service;

import com.techfirst.backend.pragmaticfp.domain.MemberNameChangedEvent;

public interface EventPublisher {
    void publish(MemberNameChangedEvent event);
}
