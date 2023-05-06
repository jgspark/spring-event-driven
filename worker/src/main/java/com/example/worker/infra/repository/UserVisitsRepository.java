package com.example.worker.infra.repository;

import com.example.worker.domain.user.UserVisits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVisitsRepository extends JpaRepository<UserVisits, Long> {

    Optional<UserVisits> findByUserId(Long userId);
}
