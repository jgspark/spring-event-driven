package com.example.producer.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private LocalDateTime registrationAt;

    private User(String email, String password, LocalDateTime registrationAt) {
        this.email = email;
        this.password = password;
        this.registrationAt = registrationAt;
    }

    public static User of(String email, String password, LocalDateTime registrationAt) {
        return new User(email, password, registrationAt);
    }
}

