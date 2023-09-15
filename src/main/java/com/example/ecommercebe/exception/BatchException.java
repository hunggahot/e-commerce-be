package com.example.ecommercebe.exception;

public class BatchException extends RuntimeException{
    public BatchException() {
        super();
    }

    public BatchException(String message) {
        super(message);
    }

    public BatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
