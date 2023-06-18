package ru.hse.kpokollok.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hse.kpokollok.data.model.ExceptionModel;
import ru.hse.kpokollok.exception.AlreadyExistsException;

@RestControllerAdvice
public class AlreadyExistsExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ExceptionModel handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}