package com.project.questapp.model.dto.response;

import com.project.questapp.model.entity.Post;
import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;

    public PostResponse(Post entity){
            this.id=entity.getId();
            this.userId=entity.getUser().getId();
            this.userName=entity.getUser().getUserName();
            this.title=entity.getTitle();
            this.text=entity.getText();
    }
}
