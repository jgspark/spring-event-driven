package com.example.worker.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVisits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime visitsAt;

    public static UserVisits of(Long userId, LocalDateTime visitsAt) {
        return new UserVisits(userId, visitsAt);
    }

    private UserVisits(Long userId, LocalDateTime visitsAt) {
        this.userId = userId;
        this.visitsAt = visitsAt;
    }

    void updatedVisitsAt(LocalDateTime visitsAt) {
        this.visitsAt = visitsAt;
    }
}
