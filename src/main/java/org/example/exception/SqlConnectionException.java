package org.example.exception;

public class SqlConnectionException extends RuntimeException{
    public SqlConnectionException(String message) {
        super(message);
    }
}
