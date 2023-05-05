package com.example.producer.service;

import com.example.producer.domain.common.Validator;
import com.example.producer.domain.common.Writer;
import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserRegister;
import com.example.producer.dto.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Writer<User> userWriter;

    private final Validator<UserRegister> userValidator;

    @Override
    public User register(UserRegisterRequest request) {

        UserRegister register = request.toEntity();

        User entity = register.register(userValidator);

        return userWriter.write(entity);
    }
}
