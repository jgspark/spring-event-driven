package com.example.producer.domain.user;

import com.example.producer.domain.common.Writer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisterEventListener implements ApplicationListener<UserRegisterEvent> {

    private final Writer<UserEvent> userEventWriter;

    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        UserEvent userEvent = UserEvent.of(event.getEventName(), convert(event.getPayload()));
        userEventWriter.write(userEvent);
    }

    private String convert(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
