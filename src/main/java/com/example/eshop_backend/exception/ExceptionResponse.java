package com.example.eshop_backend.exception;

public class ExceptionResponse {

    private final String code;
    private final String message;
    private final String timestamp;
    private final String path;

    public ExceptionResponse(String code, String message, String timestamp, String path) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
