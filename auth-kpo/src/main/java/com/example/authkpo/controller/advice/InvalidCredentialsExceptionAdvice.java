package com.example.authkpo.controller.advice;

import com.example.authkpo.data.model.ExceptionModel;
import com.example.authkpo.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidCredentialsExceptionAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ExceptionModel handleInvalidCredentialsException(InvalidCredentialsException exception) {
        return new ExceptionModel(Integer.toString(HttpStatus.UNAUTHORIZED.value()),
                                  exception.getMessage());
    }
}
