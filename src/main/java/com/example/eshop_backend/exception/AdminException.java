package com.example.eshop_backend.exception;

public class AdminException extends RuntimeException {

    private final String code;
    private final String message;
    private final String timestamp;


    public AdminException(String code, String message, String timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;

    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}


