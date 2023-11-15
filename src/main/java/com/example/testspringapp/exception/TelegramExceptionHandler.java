package com.example.testspringapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.example.testspringapp")
public class TelegramExceptionHandler {

    @ExceptionHandler(TelegramBotException.class)
    public ResponseEntity<String> handleNniImportException(final TelegramBotException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
