package com.example.worker.domain.user;

import com.example.worker.infra.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserEventWriter {

    private final UserEventRepository userEventRepository;

    @Transactional(propagation = Propagation.NESTED)
    public void end(String name) {
        UserEvent event = userEventRepository.findByName(name).orElseThrow(() -> new RuntimeException("Not Found User Event is " + name));
        event.success();
    }
}
