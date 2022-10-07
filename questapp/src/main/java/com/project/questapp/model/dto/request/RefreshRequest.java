package com.project.questapp.model.dto.request;

import lombok.Data;

@Data
public class RefreshRequest {
    private Long userId;
    private String refreshToken;

}
