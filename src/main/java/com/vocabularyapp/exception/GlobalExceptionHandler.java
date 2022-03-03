package com.vocabularyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<ValidationErrorMessage>>
        handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
            return new ResponseEntity<>(
                        ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(ValidationErrorMessage::new)
                            .collect(Collectors.toList()),
                        HttpStatus.BAD_REQUEST
            );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<SystemErrorMessage>
        handleIllegalArgumentException(IllegalArgumentException ex) {
            return new ResponseEntity<>(
                    new SystemErrorMessage(ex.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<SystemErrorMessage>
        handleUsernameNotFoundException(UsernameNotFoundException ex) {
            return new ResponseEntity<>(
                new SystemErrorMessage(ex.getMessage()),
                HttpStatus.BAD_REQUEST
            );
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ResponseEntity<SystemErrorMessage>
        handleIllegalStateException(IllegalStateException ex) {
            return new ResponseEntity<SystemErrorMessage>(
                    new SystemErrorMessage(ex.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
    }
}
