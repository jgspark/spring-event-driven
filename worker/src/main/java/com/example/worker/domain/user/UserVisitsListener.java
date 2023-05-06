package com.example.worker.domain.user;

import com.example.worker.dto.UserRegisterDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserVisitsListener {

    private final ObjectMapper objectMapper;

    // 삭제 메세지 정책 수립
    @SqsListener(value = "${sqs.queue1.name}", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void visitsListener(String message) {
        log.info("sqs message : {}", message);
        UserRegisterDto convert = convert(message, UserRegisterDto.class);
        log.info("convert : {}", convert.toString());
    }

    public <T> T convert(String message, Class<T> type) {
        try {
            return objectMapper.readValue(message, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
