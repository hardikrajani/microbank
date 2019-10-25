package com.wellOfJava.micro.demo.transactionService.exception;

import com.wellOfJava.micro.demo.commons.exception.ResourceNotFoundException;

public class TransactionNotFoundException extends ResourceNotFoundException {
    public TransactionNotFoundException(String id) {
        super(String.format("Transaction not found '%s'",id), id);
        this.id = id;
    }
}
