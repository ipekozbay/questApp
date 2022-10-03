package com.project.questapp.model.dto.response;

import com.project.questapp.model.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private  Long userId;
    private String text;
    private String userName;


    public CommentResponse(Comment entity){
        this.id=entity.getId();
        this.userId= entity.getId();
        this.text= entity.getText();
        this.userName=entity.getUser().getUserName();

    }

}
