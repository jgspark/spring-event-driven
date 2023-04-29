package com.example.consumer.infra.config.sqs;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("aws.profile")
public class Profile {

    private String region;

    private String accessKeyId;

    private String secretAccessKey;
}
