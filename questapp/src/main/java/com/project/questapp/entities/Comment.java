package com.project.questapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    private Long id;
    private Long postId;
    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    private String text;
}
