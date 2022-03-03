package com.vocabularyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.*;

@Getter @AllArgsConstructor
public class RegistrateAppUserRequest {

    @NotEmpty(message = "Username is mandatory")
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private final String username;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email must be a valid email")
    private final String email;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private final String password;
}
