package com.example.producer.infra.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsRepository implements QueueRepository {

    private final AmazonSQS amazonSQS;

    @Override
    public void send(SqsMessage sqsMessage) {

        SqsInfo info = sqsMessage.getSqsInfo();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(info.getUrl())
                .withMessageBody(sqsMessage.getMessage())
                .withMessageGroupId(sqsMessage.getGroupId())
                .withMessageDeduplicationId(sqsMessage.getGroupId());

        amazonSQS.sendMessage(sendMessageRequest);
    }
}
