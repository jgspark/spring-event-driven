package com.example.producer.infra.aws.sqs;

import com.example.producer.infra.aws.SqsInfo;
import lombok.Getter;

@Getter
public class SqsMessage {

    private SqsInfo sqsInfo;

    private String message;

    public static SqsMessage of(SqsInfo sqsInfo, String message) {
        return new SqsMessage(sqsInfo, message);
    }

    private SqsMessage(SqsInfo sqsInfo, String message) {
        this.sqsInfo = sqsInfo;
        this.message = message;
    }

    public String getGroupId() {
        return "groupId_test_" + System.currentTimeMillis();
    }
}
