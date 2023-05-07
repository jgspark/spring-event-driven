package com.example.producer.infra.repository;

import com.example.producer.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(propagation = Propagation.NESTED)
    boolean existsByEmail(String email);
}
