package com.example.producer.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String payload;

    @Column(nullable = false)
    private LocalDateTime eventAt;

    @Column
    private LocalDateTime successAt;

    public static UserEvent of(String name, String payload) {
        return new UserEvent(name, payload);
    }

    private UserEvent(String name, String payload) {
        this.name = name;
        this.payload = payload;
    }

    @PrePersist
    void insert() {
        this.eventAt = LocalDateTime.now();
    }
}
