package com.project.questapp.controller;

import com.project.questapp.model.dto.request.CommentCreateRequest;
import com.project.questapp.model.dto.request.CommentUpdateRequest;
import com.project.questapp.model.dto.response.CommentResponse;
import com.project.questapp.model.entity.Comment;
import com.project.questapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService ;
    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request){
        return commentService.createOneComment(request);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request){
        return commentService.updateOneCommentById(commentId,request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){

        commentService.deleteOneCommentById(commentId);
    }

}
