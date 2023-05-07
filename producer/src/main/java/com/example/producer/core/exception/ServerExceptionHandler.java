package com.example.producer.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exception(Exception e) {
        log.error(e.getMessage(), e.getCause(), e);
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage(), LocalDateTime.now());
    }
}
