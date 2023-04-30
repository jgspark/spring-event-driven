package com.example.producer.dto.request;

import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;

    private String password;

    public UserRegister toEntity(){
        return UserRegister.of(email , password);
    }
}
