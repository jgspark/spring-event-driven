package com.example.producer.domain.user;

import com.example.producer.domain.common.Writer;
import com.example.producer.infra.aws.sns.NotificationRepository;
import com.example.producer.infra.aws.sns.SnsInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisterEventListener implements ApplicationListener<UserRegisterEvent> {

    private final Writer<UserEvent> userEventWriter;

    private final ObjectMapper objectMapper;

    @Value("${sns.visits.topicArn}")
    private String topicArn;

    private final NotificationRepository notificationRepository;

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        String message = convert(event.getPayload());
        UserEvent userEvent = UserEvent.of(event.getEventName(), message);
        userEventWriter.write(userEvent);
        notificationRepository.publish(SnsInfo.of(topicArn, message, event.getEventName()));
    }

    private String convert(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
