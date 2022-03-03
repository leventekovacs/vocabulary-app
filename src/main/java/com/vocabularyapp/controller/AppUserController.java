package com.vocabularyapp.controller;

import com.vocabularyapp.dto.RegistrateAppUserRequest;
import com.vocabularyapp.dto.RegistrateAppUserResponse;
import com.vocabularyapp.model.AppUser;
import com.vocabularyapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    //Test
    @GetMapping("")
    public List<AppUser> getAllAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrateAppUserResponse> registrateAppUser
            (@RequestBody @Valid RegistrateAppUserRequest registrateAppUserRequest) {
        return new ResponseEntity<>(appUserService.registrateUser(registrateAppUserRequest), HttpStatus.CREATED);
    }
    
}
