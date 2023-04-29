package com.example.consumer.domain;

import com.example.consumer.dto.entity.SqsMessage;
import com.example.consumer.infra.aws.SqsRepository;
import com.example.consumer.infra.config.sqs.SqsInfo;

public class Message {

    private String message;

    public static Message of(String message) {
        return new Message(message);
    }

    private Message(String message) {
        this.message = message;
    }

    public void send(SqsRepository repository, SqsInfo sqsInfo) {
        SqsMessage send = SqsMessage.of(sqsInfo, message);
        repository.send(send);
    }
}
