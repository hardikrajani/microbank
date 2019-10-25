package com.wellOfJava.micro.demo.accountServices.exception;

import com.wellOfJava.micro.demo.commons.exception.ResourceNotFoundException;

public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String id) {
        super(String.format("Account not found '%s'",id), id);
        this.id = id;
    }
}
