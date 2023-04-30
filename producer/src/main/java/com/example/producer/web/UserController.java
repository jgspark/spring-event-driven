package com.example.producer.web;


import com.example.producer.domain.user.User;
import com.example.producer.dto.request.UserRegisterRequest;
import com.example.producer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User created(@RequestBody UserRegisterRequest request) {
        return userService.register(request);
    }
}
