package com.vocabularyapp.validation;

import com.vocabularyapp.dto.RegistrateAppUserRequest;
import com.vocabularyapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegistrateAppUserRequestValidator {

    private AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void validate(RegistrateAppUserRequest registrateAppUserRequest) {
        if(appUserRepository.existsByUsername(registrateAppUserRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if(appUserRepository.existsByEmail(registrateAppUserRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }
    }
}
