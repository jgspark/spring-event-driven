package com.example.producer.service;

import com.example.producer.domain.common.Validator;
import com.example.producer.domain.common.Writer;
import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserRegister;
import com.example.producer.dto.event.UserRegisterEvent;
import com.example.producer.dto.request.UserRegisterRequest;
import com.example.producer.core.event.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Writer<User> userWriter;

    private final Validator<UserRegister> userValidator;

    private final UserEventPublisher eventPublisher;

    /**
     * 회원 가입 시
     * 회원 가입 데이터를 검증을 한다.
     * 회원 가입 데이터를 저장을 한다.
     * 회원 가입시 로그인 히스토리를 남긴다.
     */
    @Override
    public User register(UserRegisterRequest request) {

        /**
         * 회원 가입 객체로 반환
         */
        UserRegister userRegister = request.toEntity();

        LocalDateTime now = LocalDateTime.now();

        /**
         * 회원 가입 데이터 검증
         * 회원 가입될 유저 엔티티 생성
         */
        User user = userRegister.register(userValidator, now);

        /**
         * 유저 객체의 경우 데이터를 저장만 userWriter 가 책임을 갖고 역할을 한다.
         */
        User entity = userWriter.write(user);

        /**
         * 로그인 이벤트 발행
         * 이벤트의 경우 로그인 기록에 대한 비동기 방식 처리
         */
        UserRegisterEvent event = UserRegisterEvent.of(entity.getId(), now);
        eventPublisher.publishCustomEvent(event.getName(), event.getPayload());

        return entity;
    }
}
