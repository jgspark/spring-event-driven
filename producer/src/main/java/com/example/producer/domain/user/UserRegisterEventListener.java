package com.example.producer.domain.user;

import com.example.producer.domain.common.Writer;
import com.example.producer.infra.aws.QueueRepository;
import com.example.producer.infra.aws.SqsInfo;
import com.example.producer.infra.aws.SqsMessage;
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

    private final QueueRepository queueRepository;

    private final SqsInfo sqsInfo;

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        String message = convert(event.getPayload());
        UserEvent userEvent = UserEvent.of(event.getEventName(), message);
        userEventWriter.write(userEvent);
        queueRepository.send(SqsMessage.of(sqsInfo, message));
    }

    private String convert(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
