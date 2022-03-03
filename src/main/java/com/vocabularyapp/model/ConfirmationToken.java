package com.vocabularyapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfExpiration;
    private Boolean confirmed = false;
    private LocalDateTime dateOfConfirmation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime dateOfCreate,
                             LocalDateTime dateOfExpiration,
                             AppUser appUser) {
        this.token = token;
        this.dateOfCreate = dateOfCreate;
        this.dateOfExpiration = dateOfExpiration;
        this.appUser = appUser;
    }
}
