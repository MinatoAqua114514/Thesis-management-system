package com.uml.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String message;

    public CustomException(String message) {
        this.message = message;
    }
}