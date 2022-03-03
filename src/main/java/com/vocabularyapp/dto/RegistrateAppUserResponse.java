package com.vocabularyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class RegistrateAppUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime dateOfRegistration;
}
