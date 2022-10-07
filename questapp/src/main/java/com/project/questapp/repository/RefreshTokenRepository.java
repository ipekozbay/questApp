package com.project.questapp.repository;

import com.project.questapp.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository {
    RefreshToken findByUserId(Long userId);
}
