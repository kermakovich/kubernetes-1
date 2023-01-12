package solvd.laba.ermakovich.ha.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import solvd.laba.ermakovich.ha.domain.exception.EntityAlreadyExistsException;
import solvd.laba.ermakovich.ha.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.ha.domain.exception.ResourceNotFoundException;
import solvd.laba.ermakovich.ha.web.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(ResourceNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler({IllegalOperationException.class, EntityAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEntityAlreadyExistsException(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = new ArrayList<>();
         ex.getBindingResult().getAllErrors().forEach( error -> {
                 FieldError fieldError = (FieldError) error;
                errors.add(new ErrorDto(fieldError.getField(), error.getDefaultMessage()));
         });
        return errors;
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleBindException(BindException ex) {
        List<ErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            FieldError fieldError = (FieldError) error;
            errors.add(new ErrorDto(fieldError.getField(), "can`t parse value: " + fieldError.getRejectedValue()));
        });
        return errors;
    }

//    @ExceptionHandler({RuntimeException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorFieldDto handleOtherException(RuntimeException ex) {
//        return new ErrorFieldDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }

}
