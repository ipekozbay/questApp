package com.project.questapp.service;

import com.project.questapp.model.entity.Comment;
import com.project.questapp.model.entity.Like;
import com.project.questapp.model.entity.User;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    LikeRepository likeRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;

    public UserService(UserRepository userRepository,LikeRepository likeRepository,PostRepository postRepository,CommentRepository commentRepository) {

        this.userRepository = userRepository;
        this.likeRepository =likeRepository;
        this.postRepository=postRepository;
        this.commentRepository=commentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);

    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user =userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName((newUser.getUserName()));
            foundUser.setPassword((newUser.getPassword()));
            foundUser.setAvatar(newUser.getAvatar());
            userRepository.save(foundUser);
            return foundUser;
        }else{
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<Object> getUserActivity(Long userId) {
       List<Long> postIds = postRepository.findTopByUserId(userId);
       if(postIds.isEmpty())
           return null;
       List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
       List<Object> likes  =likeRepository.findUserLikesByPostId(postIds);
       List<Object> result = new ArrayList<>();
       result.addAll(comments);
       result.addAll(likes);
       return result;
    }
}
