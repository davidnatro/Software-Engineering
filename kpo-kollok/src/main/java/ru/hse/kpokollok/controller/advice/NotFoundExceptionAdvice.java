package ru.hse.kpokollok.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hse.kpokollok.data.model.ExceptionModel;
import ru.hse.kpokollok.exception.NotFoundException;

@RestControllerAdvice
public class NotFoundExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionModel handleNotFoundException(NotFoundException exception) {
        return new ExceptionModel(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
