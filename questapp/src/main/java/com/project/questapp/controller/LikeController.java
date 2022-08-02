package com.project.questapp.controller;

import com.project.questapp.model.dto.request.LikeCreateRequest;
import com.project.questapp.model.dto.request.LikeUpdateRequest;
import com.project.questapp.service.LikeService;
import org.springframework.web.bind.annotation.*;
import com.project.questapp.model.entity.Like;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService ;

    public LikeController(LikeService likeService) {

        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId,
                                  @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){

        return  likeService.createOneLike(request);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }

}
