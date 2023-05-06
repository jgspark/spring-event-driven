package com.example.worker.dto;

import com.example.worker.domain.user.UserVisits;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterDto {

    @JsonAlias("user_id")
    @JsonProperty("user_id")
    private Long userId;

    @JsonAlias("registration_at")
    @JsonProperty("registration_at")
    private LocalDateTime registrationAt;

    @Override
    public String toString() {
        return "UserRegister{" +
                "userId=" + userId +
                ", registrationAt=" + registrationAt +
                '}';
    }

    public UserVisits toEntity() {
        return UserVisits.of(userId, registrationAt);
    }
}
