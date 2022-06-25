package com.exceptions;

public class CustomException extends RuntimeException {
    public CustomException() {
        super("The custom error has been thrown!");
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
