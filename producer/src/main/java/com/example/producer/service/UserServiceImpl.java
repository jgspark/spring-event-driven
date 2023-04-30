package com.example.producer.service;

import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserRegister;
import com.example.producer.domain.user.UserValidator;
import com.example.producer.domain.user.UserWriter;
import com.example.producer.dto.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserWriter userWriter;

    private final UserValidator userValidator;

    @Override
    public User register(UserRegisterRequest request) {

        UserRegister register = request.toEntity();

        User entity = register.register(userValidator);

        return userWriter.writer(entity);
    }
}
