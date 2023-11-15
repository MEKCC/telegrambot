package com.example.testspringapp.exception;

public class TelegramBotException extends RuntimeException {

    public TelegramBotException(String message) {
        super(message);
    }
}
