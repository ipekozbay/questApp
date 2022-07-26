package com.project.questapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "p_likes")
@Data
public class Like {

    @Id
    private Long id;
    private Long postId;
    private Long userId;
}
