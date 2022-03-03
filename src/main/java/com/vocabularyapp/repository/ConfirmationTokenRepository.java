package com.vocabularyapp.repository;

import com.vocabularyapp.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    boolean existsByToken(String token);
    ConfirmationToken findByToken(String token);
}
