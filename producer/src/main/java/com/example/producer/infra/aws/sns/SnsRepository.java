package com.example.producer.infra.aws.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SnsRepository implements NotificationRepository {

    private final AmazonSNS amazonSNS;

    @Override
    public void publish(SnsInfo snsInfo) {
        String topicArn = snsInfo.getTopicArn();
        String message = snsInfo.getMessage();
        PublishRequest request = new PublishRequest(topicArn, message);
        request.setMessageGroupId(snsInfo.getGroupId());
        request.setMessageDeduplicationId(snsInfo.getGroupId());
        amazonSNS.publish(request);
    }
}
