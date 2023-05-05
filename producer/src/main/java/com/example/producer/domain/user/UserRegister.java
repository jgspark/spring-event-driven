package com.example.producer.domain.user;

import com.example.producer.domain.common.Validator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegister {

    private String email;

    private String password;

    /**
     * 유저 회원 가입 객체에서는 검증을 Validator 로 위임 한다.
     * 검증이 완료가 되면, 유저 엔티티로 데이터 반환
     */
    public User register(Validator<UserRegister> userValidator, LocalDateTime now) {
        userValidator.validation(this);
        return User.of(email, password, now);
    }

    public static UserRegister of(String email, String password) {
        return new UserRegister(email, password);
    }
}
