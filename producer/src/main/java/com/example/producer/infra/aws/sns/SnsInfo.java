package com.example.producer.infra.aws.sns;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SnsInfo {

    private String topicArn;

    private String message;

    private String groupId;

    public static SnsInfo of(String topicArn, String message, String groupId) {
        return new SnsInfo(topicArn, message, groupId);
    }
}
