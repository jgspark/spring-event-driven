package com.example.producer.service;

import com.example.producer.domain.user.User;
import com.example.producer.dto.request.UserRegisterRequest;

public interface UserService {

    User register(UserRegisterRequest request);
}
