package com.project.questapp.service;

import com.project.questapp.model.dto.request.PostCreateRequest;
import com.project.questapp.model.dto.request.PostUpdateRequest;
import com.project.questapp.model.dto.response.LikeResponse;
import com.project.questapp.model.dto.response.PostResponse;
import com.project.questapp.model.entity.Post;
import com.project.questapp.model.entity.User;
import com.project.questapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository,LikeService likeService,UserService userService) {

        this.postRepository = postRepository;
        this.likeService=likeService;
        this.userService=userService;
    }
    @Autowired
    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list ;
        if (userId.isPresent()){
            list=postRepository.findByUserId(userId.get());
        }
        else {
            list= postRepository.findAll();
        }
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.empty(),Optional.ofNullable(p.getId()));
            return new PostResponse(p,likes); }).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {

        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
 //  if (user==null)
 //        return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser((user));
        toSave.setCreateDate(new Date());

        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId){
        postRepository.deleteById(postId);
    }
}
