package com.example.consumer.web;

import com.example.consumer.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
@RequiredArgsConstructor
public class SendController {

    private final SendMessageService sendMessageService;

    @PostMapping
    public void send(String message) {
        sendMessageService.send(message);
    }
}
