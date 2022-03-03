package com.vocabularyapp.validation;

import com.vocabularyapp.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ConfirmationTokenValidator {

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void validate(String token) {
        if(!confirmationTokenRepository.existsByToken(token)) {
            throw new IllegalArgumentException("Token not found");
        }
    }
}
