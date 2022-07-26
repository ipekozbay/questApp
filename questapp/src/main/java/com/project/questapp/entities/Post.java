package com.project.questapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    private Long id;
    private Long userId;
    private String title;

    @Lob
    @Column(columnDefinition = "text")
    String text;

}
