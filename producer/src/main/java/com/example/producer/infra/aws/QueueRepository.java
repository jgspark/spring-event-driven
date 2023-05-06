package com.example.producer.infra.aws;

public interface QueueRepository {

    void send(SqsMessage sqsMessage);
}
