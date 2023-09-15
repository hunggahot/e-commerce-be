package com.example.ecommercebe.exception;

public class InventoryException extends RuntimeException{
    public InventoryException() {
        super();
    }

    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
