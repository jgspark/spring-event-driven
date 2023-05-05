package com.example.producer.service;

import com.example.producer.domain.common.Validator;
import com.example.producer.domain.common.Writer;
import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserRegister;
import com.example.producer.dto.request.UserRegisterRequest;
import com.example.producer.service.event.CustomSpringEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Writer<User> userWriter;

    private final Validator<UserRegister> userValidator;

    private final CustomSpringEventPublisher eventPublisher;

    @Override
    public User register(UserRegisterRequest request) {

        UserRegister userRegister = request.toEntity();

        User user = userRegister.register(userValidator);

        User entity = userWriter.write(user);

        eventPublisher.publishCustomEvent("test...");

        return entity;
    }
}
