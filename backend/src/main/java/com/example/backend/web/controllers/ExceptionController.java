package com.example.backend.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.exception.modelsexception.Forbidden_403;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.exception.modelsexception.Unauthorized_401;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(Unauthorized_401.class)
    public ResponseEntity<String> UnauthorizedException(Unauthorized_401 ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(Forbidden_403.class)
    public ResponseEntity<String> ForbiddenException(Forbidden_403 ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(NotFound_404.class)
    public ResponseEntity<String> NotFoundException(NotFound_404 ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
