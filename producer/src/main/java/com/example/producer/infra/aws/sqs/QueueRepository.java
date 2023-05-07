package com.example.producer.infra.aws.sqs;

public interface QueueRepository {

    void send(SqsMessage sqsMessage);
}
