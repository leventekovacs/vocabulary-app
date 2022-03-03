package com.vocabularyapp.controller;

import com.vocabularyapp.dto.ConfirmAppUserResponse;
import com.vocabularyapp.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationTokenController {

    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    public void setConfirmationTokenService(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping
    public ResponseEntity<ConfirmAppUserResponse> confirmAppUser(@RequestParam String token) {
        return new ResponseEntity<>(confirmationTokenService.confirmConfirmationToken(token), HttpStatus.OK);
    }
}
