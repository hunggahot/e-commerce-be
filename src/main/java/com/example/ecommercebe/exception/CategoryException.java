package com.example.ecommercebe.exception;

public class CategoryException extends RuntimeException{

    public CategoryException() {
        super();
    }

    public CategoryException(String message){
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
