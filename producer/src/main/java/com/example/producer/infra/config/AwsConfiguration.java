package com.example.producer.infra.config;

import com.amazonaws.ClientConfiguration;
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
     * todo: 트러블 슈팅 커넥션 pool 에서 에러가 발생이됨
     * default 상테에서에러가 발생이됨
     * 해결?: 해당 pool 의 갯수를 테스트 하는 request 양의 절반으로 늘려줌
     */
    @Bean
    public AmazonSNSAsync amazonSNSAsync() {

        ClientConfiguration clientConfiguration = new ClientConfiguration();

        // 메서드는 클라이언트가 유지할 수 있는 최대 HTTP 연결 수를 설정합니다.
        clientConfiguration.setMaxConnections(3000);

        // 10 seconds
        // 메서드는 HTTP 연결을 시도할 때 타임아웃 시간을 설정합니다.
        clientConfiguration.setConnectionTimeout(10000);

        // Connection Pool 에서 30초 동안 idle 상태인 connection 은 닫히고 Connection Pool 에서 제거
        clientConfiguration.setConnectionMaxIdleMillis(30000);

        // 커넥션 에러가 발생이 되면 retry
        clientConfiguration.setMaxErrorRetry(5);

        return AmazonSNSAsyncClientBuilder.standard()
                .withEndpointConfiguration(clientBuilderConfiguration())
                .withCredentials(awsStaticCredentialsProvider())
                .withClientConfiguration(clientConfiguration)
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
