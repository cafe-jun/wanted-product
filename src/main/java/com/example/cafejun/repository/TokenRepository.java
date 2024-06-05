package com.example.cafejun.repository;

import com.example.cafejun.domain.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByEmail(String email);
    Optional<Token> findByRefreshToken(String refreshToken);
}
