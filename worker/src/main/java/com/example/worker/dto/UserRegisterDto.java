package com.example.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    private Long userId;

    private LocalDateTime registrationAt;

    @Override
    public String toString() {
        return "UserRegister{" +
                "userId=" + userId +
                ", registrationAt=" + registrationAt +
                '}';
    }
}
