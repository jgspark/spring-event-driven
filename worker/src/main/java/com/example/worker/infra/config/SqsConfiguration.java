package com.example.worker.infra.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.example.worker.infra.sqs.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.core.env.ResourceIdResolver;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.listener.QueueMessageHandler;
import io.awspring.cloud.messaging.listener.SimpleMessageListenerContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class SqsConfiguration {

    private final Profile profile;

    @Value("${sqs.host}")
    private String sqlUrl;

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqlUrl, profile.getRegion())).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(profile.getAccessKeyId(), profile.getSecretAccessKey()))).build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsync(), (ResourceIdResolver) null, mappingJackson2MessageConverter(messageConverter()));
    }

    @Bean
    public ObjectMapper messageConverter() {
        return new ObjectMapper().findAndRegisterModules();
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter(final ObjectMapper objectMapper) {
        final var jacksonMessageConverter = new MappingJackson2MessageConverter();
        jacksonMessageConverter.setObjectMapper(objectMapper);
        jacksonMessageConverter.setSerializedPayloadClass(String.class);
        jacksonMessageConverter.setStrictContentTypeMatch(false);
        return jacksonMessageConverter;
    }


    /**
     * User To Message Bean
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(AmazonSQSAsync amazonSqs, QueueMessageHandler queueMessageHandler) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAmazonSqs(amazonSqs);
        container.setMessageHandler(queueMessageHandler);
        container.setMaxNumberOfMessages(10);
        container.setTaskExecutor(taskExecutor());
        // 대기 시간을 60초로 설정
        container.setWaitTimeOut(10);
        return container;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("sqs-test-listener-");
        return executor;
    }

    @Bean
    public QueueMessageHandler queueMessageHandler() {
        QueueMessageHandlerFactory queueMessageHandlerFactory = new QueueMessageHandlerFactory();
        queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsync());
        return queueMessageHandlerFactory.createQueueMessageHandler();
    }
}
