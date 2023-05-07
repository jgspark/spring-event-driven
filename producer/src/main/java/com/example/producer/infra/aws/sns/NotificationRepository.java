package com.example.producer.infra.aws.sns;

public interface NotificationRepository {
    void publish(SnsInfo snsInfo);
}
