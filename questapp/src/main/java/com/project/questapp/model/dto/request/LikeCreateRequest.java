package com.project.questapp.model.dto.request;

import lombok.Data;

@Data
public class LikeCreateRequest {

    private Long id;
    private Long userId;
    private Long postId;
}
