package com.example.authkpo.controller.advice;

import com.example.authkpo.data.model.ExceptionModel;
import com.example.authkpo.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyExistsExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ExceptionModel handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ExceptionModel(Integer.toString(HttpStatus.BAD_REQUEST.value()),
                                  exception.getMessage());
    }
}
