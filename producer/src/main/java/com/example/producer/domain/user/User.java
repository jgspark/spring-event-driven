package com.example.producer.domain.user;

import com.example.producer.domain.common.Validator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime registrationAt;

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User of(String email, String password) {
        return new User(email, password);
    }

    @Transient
    public void register(Validator<User> userValidator, LocalDateTime registrationAt) {
        userValidator.validation(this);
        setRegistrationAt(registrationAt);
        registerEvent(new UserRegisterEvent(this, this));
    }

    private void setRegistrationAt(LocalDateTime registrationAt) {
        this.registrationAt = registrationAt;
    }
}

