package com.example.producer.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserEventMessage extends ApplicationEvent {

    private final String eventName;

    private final Object eventPayload;

    public static UserEventMessage of(Object source, String eventName, Object eventPayload) {
        return new UserEventMessage(source, eventName, eventPayload);
    }

    UserEventMessage(Object source, String eventName, Object eventPayload) {
        super(source);
        this.eventName = eventName;
        this.eventPayload = eventPayload;
    }

}
