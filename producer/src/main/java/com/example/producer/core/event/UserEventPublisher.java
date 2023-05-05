package com.example.producer.core.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(String eventName, Object payload) {
        UserEventMessage userEventMessage = UserEventMessage.of(this, eventName, payload);
        applicationEventPublisher.publishEvent(userEventMessage);
    }
}
