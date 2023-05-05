package com.example.producer.domain.user;

import com.example.producer.domain.common.Writer;
import com.example.producer.infra.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserEventWriter implements Writer<UserEvent> {

    private final UserEventRepository userEventRepository;

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public UserEvent write(UserEvent userEvent) {
        return userEventRepository.save(userEvent);
    }
}
