package com.example.worker.infra.repository;

import com.example.worker.domain.user.UserVisitsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVisitsHistoryRepository extends JpaRepository<UserVisitsHistory, Long> {

}
