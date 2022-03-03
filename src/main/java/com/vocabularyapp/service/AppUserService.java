package com.vocabularyapp.service;

import com.vocabularyapp.constants.AppUserRole;
import com.vocabularyapp.dto.RegistrateAppUserRequest;
import com.vocabularyapp.dto.RegistrateAppUserResponse;
import com.vocabularyapp.model.AppUser;
import com.vocabularyapp.model.ConfirmationToken;
import com.vocabularyapp.repository.AppUserRepository;
import com.vocabularyapp.security.PasswordEncoder;
import com.vocabularyapp.validation.RegistrateAppUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private RegistrateAppUserRequestValidator registrateAppUserRequestValidator;
    private PasswordEncoder passwordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    //testingPurposes
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public RegistrateAppUserResponse registrateUser(RegistrateAppUserRequest registrateAppUserRequest) {

        //username and email uniqueness validation
        registrateAppUserRequestValidator.validate(registrateAppUserRequest);

        //create and save user
        AppUser appUser = new AppUser(
                registrateAppUserRequest.getUsername(),
                registrateAppUserRequest.getEmail(),
                passwordEncoder.bCryptPasswordEncoder().encode(registrateAppUserRequest.getPassword()),
                LocalDateTime.now(),
                AppUserRole.USER
        );

        //save user
        AppUser savedAppUser = appUserRepository.save(appUser);

        //create confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                appUser
        );

        //save confirmation token
        confirmationTokenService.addConfirmationToken(confirmationToken);
        System.out.println(confirmationToken.getToken());

        //TODO sending email

        return new RegistrateAppUserResponse(
                savedAppUser.getId(),
                savedAppUser.getUsername(),
                savedAppUser.getEmail(),
                savedAppUser.getDateOfRegistration()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Username '" + username + "' not found"
                ));
    }

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    public void setEmailUniqueValidator(RegistrateAppUserRequestValidator registrateAppUserRequestValidator) {
        this.registrateAppUserRequestValidator = registrateAppUserRequestValidator;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setConfirmationTokenService(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }
}
