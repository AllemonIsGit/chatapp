package com.example.chatapp.domain.exception;

public class PasswordDoNotMatchException extends RuntimeException {

    public PasswordDoNotMatchException(String message) {
        super(message);
    }
}
