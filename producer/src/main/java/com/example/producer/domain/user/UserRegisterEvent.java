package com.example.producer.domain.user;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.Map;

public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    private LocalDateTime getRegistrationAt() {
        return user.getRegistrationAt();
    }

    private Long getUserId() {
        return user.getId();
    }

    String getEventName() {
        return String.format("REGISTER:%s", getUserId());
    }

    Map<String, Object> getPayload() {
        return Map.of(
                "userId", getUserId(),
                "registrationAt", getRegistrationAt()
        );
    }
}
