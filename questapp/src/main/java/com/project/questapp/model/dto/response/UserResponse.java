package com.project.questapp.model.dto.response;

import com.project.questapp.model.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private Integer avatarId;
    private String userName;

    public UserResponse(User entity) {
        this.id=entity.getId();
        this.avatarId=entity.getAvatar();
        this.userName=entity.getUserName();

    }

}
