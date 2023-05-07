package com.example.producer.domain.user;

import com.example.producer.domain.common.Writer;
import com.example.producer.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserWriter implements Writer<User> {

    private final UserRepository userRepository;

    @Override
    public User write(User user) {
        return userRepository.save(user);
    }
}
