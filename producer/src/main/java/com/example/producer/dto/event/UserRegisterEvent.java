package com.example.producer.dto.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterEvent {

    private String name;

    private Map<String, Object> payload;

    public static UserRegisterEvent of(Long id, LocalDateTime registrationAt) {
        return new UserRegisterEvent("REGISTER:" + id, setPayload(id, registrationAt));
    }

    private static Map<String, Object> setPayload(Long id, LocalDateTime registrationAt) {
        return Map.of(
                "id", id,
                "registrationAt", registrationAt
        );
    }

}
