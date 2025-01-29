package com.codingnomads.springsecurity.authorization.custompermissions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({Exception.class})
    public String handle(Exception e) {
        return e.getMessage();
    }
}
