package com.vocabularyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ConfirmAppUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final Boolean enabled;
    private final Long tokenId;
    private final String token;
    private final LocalDateTime dateOfConfirmation;
    private final Boolean tokenConfirmed;
}
