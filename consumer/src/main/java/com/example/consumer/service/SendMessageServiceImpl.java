package com.example.consumer.service;

import com.example.consumer.dto.entity.SqsMessage;
import com.example.consumer.infra.aws.SqsRepository;
import com.example.consumer.infra.config.sqs.SqsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    private final SqsRepository sqsRepository;

    private final SqsInfo sqsInfo;

    @Override
    public void send(String msg) {
        SqsMessage dto = SqsMessage.of(sqsInfo, msg);
        sqsRepository.send(dto);
    }
}
