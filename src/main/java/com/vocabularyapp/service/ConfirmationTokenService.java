package com.vocabularyapp.service;

import com.vocabularyapp.dto.ConfirmAppUserResponse;
import com.vocabularyapp.model.ConfirmationToken;
import com.vocabularyapp.repository.ConfirmationTokenRepository;
import com.vocabularyapp.validation.ConfirmationTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;
    private ConfirmationTokenValidator confirmationTokenValidator;



    public void addConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Transactional
    public ConfirmAppUserResponse confirmConfirmationToken(String token) {

        //token validation
        confirmationTokenValidator.validate(token);

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

        //setting user enabled and confirmation token date of confirmation
        if(LocalDateTime.now().isBefore(confirmationToken.getDateOfExpiration())
                && confirmationToken.getConfirmed() == false) {
            confirmationToken.getAppUser().setEnabled(true);
            confirmationToken.setConfirmed(true);
            confirmationToken.setDateOfConfirmation(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Token has expired");
            //TODO if token expired send another token
        }


        return new ConfirmAppUserResponse(
                confirmationToken.getAppUser().getId(),
                confirmationToken.getAppUser().getUsername(),
                confirmationToken.getAppUser().getEmail(),
                confirmationToken.getAppUser().getEnabled(),
                confirmationToken.getId(),
                confirmationToken.getToken(),
                confirmationToken.getDateOfConfirmation(),
                confirmationToken.getConfirmed()
        );
    }

    @Autowired
    public void setConfirmationTokenValidator(ConfirmationTokenValidator confirmationTokenValidator) {
        this.confirmationTokenValidator = confirmationTokenValidator;
    }

    @Autowired
    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }
}
