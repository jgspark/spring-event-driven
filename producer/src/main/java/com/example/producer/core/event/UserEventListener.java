package com.example.producer.core.event;

import com.example.producer.domain.common.Writer;
import com.example.producer.domain.user.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class UserEventListener implements ApplicationListener<UserEventMessage> {

    private final Writer<UserEvent> userEventWriter;

    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(UserEventMessage event) {
        String payload = convert(event.getEventPayload());
        UserEvent userEvent = UserEvent.of(event.getEventName(), payload);
        userEventWriter.write(userEvent);
    }

    private String convert(Object payload) {

        if (ObjectUtils.isEmpty(payload)) return null;

        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
