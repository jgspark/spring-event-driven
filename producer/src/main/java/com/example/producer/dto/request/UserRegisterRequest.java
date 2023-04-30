package com.example.producer.dto.request;

import com.example.producer.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;

    private String password;

    public User toEntity(){
        return User.of(email , password);
    }
}
