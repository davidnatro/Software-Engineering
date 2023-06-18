package com.example.authkpo.controller.advice;

import com.example.authkpo.data.model.ExceptionModel;
import com.example.authkpo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionModel handleNotFoundException(NotFoundException exception) {
        return new ExceptionModel(Integer.toString(HttpStatus.NOT_FOUND.value()),
                                  exception.getMessage());
    }
}
