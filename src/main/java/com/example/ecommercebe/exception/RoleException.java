package com.example.ecommercebe.exception;

public class RoleException extends RuntimeException{
    public RoleException(){
        super();
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }
}
