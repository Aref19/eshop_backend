package com.example.eshop_backend.exception;

public class NotFoundException extends RuntimeException{
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
