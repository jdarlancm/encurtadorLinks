package br.com.jdarlan.encurtador_link_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentExcetion(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
