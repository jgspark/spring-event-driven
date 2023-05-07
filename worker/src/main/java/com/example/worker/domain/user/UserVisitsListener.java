package com.example.worker.domain.user;

import com.example.worker.dto.UserRegisterDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserVisitsListener {

    private final UserVisitsWriter userVisitsWriter;

    private final UserEventWriter userEventWriter;

    private final ObjectMapper objectMapper;

    // 삭제 메세지 정책 수립
    @SqsListener(value = "${sqs.queue1.name}", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void visitsListener(String message) {

        // 객체로 파싱
        UserRegisterDto dto = convert(message, UserRegisterDto.class);

        // dto 는 UserVisits 엔티티로 변환
        UserVisits entity = dto.toEntity();

        // 엔티티 저장
        userVisitsWriter.write(entity);

        // 이벤트 종료
        userEventWriter.end(dto.getEventName());
    }

    <T> T convert(String message, Class<T> type) {
        try {
            Map<String, Object> map = objectMapper.readValue(message, Map.class);
            return objectMapper.readValue((String) map.get("Message"), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
