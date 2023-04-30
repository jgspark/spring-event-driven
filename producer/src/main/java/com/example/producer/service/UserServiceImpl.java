package com.example.producer.service;

import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserWriter;
import com.example.producer.dto.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserWriter userWriter;

    @Override
    public User register(UserRegisterRequest request) {
        return userWriter.register(request.toEntity());
    }
}
