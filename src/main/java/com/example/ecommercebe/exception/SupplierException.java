package com.example.ecommercebe.exception;

import com.example.ecommercebe.entity.Supplier;

public class SupplierException extends Exception {

    public SupplierException() {
        super();
    }

    public SupplierException (String message) {
        super(message);
    }

    public SupplierException(String message, Throwable cause) {
        super(message, cause);
    }
}
