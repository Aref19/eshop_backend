package com.example.eshop_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> adminExceptions(String message){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException(message));

    }
}
