package org.example.exception;

public class PacjentNotFoundException extends RuntimeException {
    public PacjentNotFoundException(String message) {
        super(message);
    }
}
