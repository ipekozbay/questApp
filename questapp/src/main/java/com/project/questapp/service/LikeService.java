package com.project.questapp.service;

import com.project.questapp.model.dto.request.LikeCreateRequest;
import com.project.questapp.model.dto.request.LikeUpdateRequest;
import com.project.questapp.model.dto.response.LikeResponse;
import com.project.questapp.model.entity.Like;
import com.project.questapp.model.entity.Post;
import com.project.questapp.model.entity.User;
import com.project.questapp.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private  UserService userService;
    private PostService postService;


    public LikeService(LikeRepository likeRepository, UserService userService,PostService postService) {
        this.likeRepository = likeRepository;
        this.userService=userService;
        this.postService=postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            list= likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list= likeRepository.findByPostId(postId.get());
        } else
            list= likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {

        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());

        if(user!=null && post!=null){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }


    public Like updateOneLikeById(Long likeId, LikeUpdateRequest request) {
        Optional<Like> like = likeRepository.findById(likeId);

        if (like.isPresent()){
            Like likeToUpdate = like.get();
            return likeRepository.save(likeToUpdate);
        }else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}