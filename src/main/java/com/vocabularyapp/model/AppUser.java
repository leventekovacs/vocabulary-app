package com.vocabularyapp.model;

import com.vocabularyapp.constants.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table (
        uniqueConstraints  =  {
                @UniqueConstraint( name = "username_unique", columnNames = "username"),
                @UniqueConstraint( name = "email_unique", columnNames = "email")
        }
)
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime dateOfRegistration;
    private LocalDateTime dateOfUpdate;
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser")
    private Collection<Expression> expressions = new HashSet<>();

    public AppUser(String username, String email, String password,
                   LocalDateTime dateOfRegistration, AppUserRole appUserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(appUserRole.name()));
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
