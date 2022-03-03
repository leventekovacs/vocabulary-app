package com.vocabularyapp.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ValidationErrorMessage {

    private String field;
    private String message;

    public ValidationErrorMessage(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
