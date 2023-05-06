package com.example.producer.core.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String code,
        String message,
        LocalDateTime time) {
}
