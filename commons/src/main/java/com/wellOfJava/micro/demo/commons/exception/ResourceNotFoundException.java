package com.wellOfJava.micro.demo.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    protected String id;

    public ResourceNotFoundException(String message, String Id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
