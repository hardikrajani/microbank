package com.wellOfJava.micro.demo.commons.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
