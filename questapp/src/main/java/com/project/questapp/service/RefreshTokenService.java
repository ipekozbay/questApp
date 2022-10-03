package com.project.questapp.service;

import com.project.questapp.model.entity.RefreshToken;
import com.project.questapp.model.entity.User;
import com.project.questapp.repository.ResfreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${refresh.token.expires.in}")
    private Long expireSeconds;
    private ResfreshTokenRepository resfreshTokenRepository;
    public RefreshTokenService(ResfreshTokenRepository resfreshTokenRepository) {
        this.resfreshTokenRepository = resfreshTokenRepository;
    }

    public RefreshToken createRefreshToken(User user){
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));

    }
    public boolean isRefreshExpired(RefreshToken token){
        return token.getExpiryDate().before(new Date());
    }
}
