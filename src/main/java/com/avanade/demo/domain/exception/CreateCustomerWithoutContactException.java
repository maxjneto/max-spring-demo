package com.avanade.demo.domain.exception;

public class CreateCustomerWithoutContactException extends RuntimeException {
    public CreateCustomerWithoutContactException(String message) {
        super(message);
    }
}
