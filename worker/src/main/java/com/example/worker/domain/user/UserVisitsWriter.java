package com.example.worker.domain.user;

import com.example.worker.infra.repository.UserVisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class UserVisitsWriter {

    private final UserVisitsRepository userVisitsRepository;

    public UserVisits write(UserVisits entity) {

        Optional<UserVisits> userVisitsOptional = userVisitsRepository.findByUserId(entity.getUserId());

        if (userVisitsOptional.isPresent()) {
            UserVisits userVisits = userVisitsOptional.get();
            userVisits.updatedVisitsAt(entity.getVisitsAt());
        } else {
            userVisitsRepository.save(entity);
        }

        return entity;
    }
}
