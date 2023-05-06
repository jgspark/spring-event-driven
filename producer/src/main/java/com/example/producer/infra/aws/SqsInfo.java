package com.example.producer.infra.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("sqs.queue1")
public class SqsInfo {

    private String name;

    private String url;
}
