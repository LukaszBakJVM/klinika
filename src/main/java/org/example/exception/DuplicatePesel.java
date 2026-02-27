package org.example.exception;

public class DuplicatePesel extends RuntimeException{
    public DuplicatePesel(String message) {
        super(message);
    }
}
