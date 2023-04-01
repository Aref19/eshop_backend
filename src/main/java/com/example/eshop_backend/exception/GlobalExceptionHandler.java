package com.example.eshop_backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleException(ResponseStatusException ex, WebRequest request) {
        var response = new ExceptionResponse(
                String.valueOf(ex.getStatusCode().value()),
                ex.getReason(),
                Instant.now().toString(),
                ((ServletWebRequest) request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(response, ex.getStatusCode());
    }



}

