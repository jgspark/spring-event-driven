package com.example.consumer.infra.config.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfiguration {

    @Bean
    public AmazonSQSAsync amazonSQSAws(Profile profile) {

        BasicAWSCredentials basicAWSCredentials =
                new BasicAWSCredentials(profile.getAccessKeyId(), profile.getSecretAccessKey());

        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(profile.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
