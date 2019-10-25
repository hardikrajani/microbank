package com.wellOfJava.micro.demo.userServices.exception;

import com.wellOfJava.micro.demo.commons.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String id) {
        super(String.format("User not found '%s'",id), id);
        this.id = id;
    }
}
