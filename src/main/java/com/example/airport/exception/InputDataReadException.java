package com.example.airport.exception;

public class InputDataReadException extends RuntimeException {

    public InputDataReadException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InputDataReadException(final String message) {
        super(message);
    }
}
