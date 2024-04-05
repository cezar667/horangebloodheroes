package com.processo.horangebloodheroes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("o combate é inválido e não foi computado.");
    }

    @ExceptionHandler(HeroOutOfCombatException.class)
    public ResponseEntity<String> handleIllegalStateException(HeroOutOfCombatException ex) {
        return ResponseEntity.status(HttpStatus.GONE).body("o herói não está em condições de combater no momento.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Required request parameter '" + ex.getParameterName() + "' is missing");
    }
}
