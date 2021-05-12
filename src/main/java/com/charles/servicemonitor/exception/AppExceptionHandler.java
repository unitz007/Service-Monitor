package com.charles.servicemonitor.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exce(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    
}
