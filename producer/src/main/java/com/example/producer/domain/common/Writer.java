package com.example.producer.domain.common;

public interface Writer<T> {

    T write(T t);
}
