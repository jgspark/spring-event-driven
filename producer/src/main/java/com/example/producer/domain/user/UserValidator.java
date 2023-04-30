package com.example.producer.domain.user;

import com.example.producer.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validation(UserRegister register) {

        if (ObjectUtils.isEmpty(register.getEmail())) {
            throw new IllegalArgumentException("Email is Empty");
        }

        if (ObjectUtils.isEmpty(register.getPassword())) {
            throw new IllegalArgumentException("Password is Empty");
        }

        if (isNotValidEmail(register.getEmail())) {
            throw new RuntimeException("email format is not match");
        }

        if (userRepository.existsByEmail(register.getEmail())) {
            throw new RuntimeException("ready to email");
        }
    }

    private boolean isNotValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            err = true;
        }
        return !err;
    }
}
