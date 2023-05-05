package com.example.producer.domain.user;

import com.example.producer.domain.common.Validator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegister {

    private String email;

    private String password;

    public User register(Validator<UserRegister> userValidator) {
        userValidator.validation(this);
        return User.of(email, password);
    }

    public static UserRegister of(String email, String password) {
        return new UserRegister(email, password);
    }
}
