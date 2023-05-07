package com.example.producer.infra.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.example.producer.infra.aws.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsConfiguration {

    private final Profile profile;

    @Value("${sqs.host}")
    private String sqlUrl;


    /**
     * SQS Bean 등록
     */
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(clientBuilderConfiguration())
                .withCredentials(awsStaticCredentialsProvider())
                .build();
    }

    /**
     * SNS Bean 등록
     */
    @Bean
    public AmazonSNSAsync amazonSNSAsync() {
        return AmazonSNSAsyncClientBuilder.standard()
                .withEndpointConfiguration(clientBuilderConfiguration())
                .withCredentials(awsStaticCredentialsProvider())
                .build();
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration clientBuilderConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(sqlUrl, profile.getRegion());
    }

    @Bean
    public AWSStaticCredentialsProvider awsStaticCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(profile.getAccessKeyId(), profile.getSecretAccessKey()));
    }
}
