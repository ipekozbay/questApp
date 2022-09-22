package com.project.questapp.controller;

import com.project.questapp.model.dto.request.PostCreateRequest;
import com.project.questapp.model.dto.request.PostUpdateRequest;
import com.project.questapp.model.dto.response.PostResponse;
import com.project.questapp.model.entity.Post;
import com.project.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){

        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public PostResponse getOnePost(@RequestParam Long postId){
        return postService.getOnePostByIdWithLikes(postId);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }

}

