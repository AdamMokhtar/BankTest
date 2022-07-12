package com.capgemini.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TargetNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TargetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String targetNotFoundHandler(TargetNotFoundException ex) {
        return ex.getMessage();
    }
}