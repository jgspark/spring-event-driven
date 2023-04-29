package com.example.consumer.infra.aws;

import com.example.consumer.dto.entity.SqsMessage;

public interface SqsRepository {

    void send(SqsMessage message);
}
