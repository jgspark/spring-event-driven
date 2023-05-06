package com.example.worker.domain.user;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserVisitsListener {

    /**
     * 삭제 메세지 정책 수립
     *
     * @param snsMessage
     */
    @SqsListener(value = "${sqs.queue1.name}", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void testSqsListener(String snsMessage) {
        log.info("sqs message : {}", snsMessage);
    }
}
