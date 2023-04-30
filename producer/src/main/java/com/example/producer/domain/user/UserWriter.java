package com.example.producer.domain.user;

import com.example.producer.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserWriter {

    private final UserRepository userRepository;

    @Transactional
    public User writer(User user) {
        return userRepository.save(user);
    }
}
