package com.example.producer.domain.common;

public interface Validator<T> {

    void validation(T data);
}
