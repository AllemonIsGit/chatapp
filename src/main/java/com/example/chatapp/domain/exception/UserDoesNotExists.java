package com.example.chatapp.domain.exception;

public class UserDoesNotExists extends RuntimeException {
    public UserDoesNotExists(String message) {
        super(message);
    }
}
