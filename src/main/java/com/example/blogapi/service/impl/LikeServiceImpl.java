package com.example.blogapi.service.impl;

import com.example.blogapi.enums.LikeStatus;
import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.model.*;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.repository.*;
import com.example.blogapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    LikeCommentRepository likeCommentRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public ApiResponse likePost(Long postId, Long userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);
        LikePost like = new LikePost();
        Optional<LikePost> likeAndUnlike = likeRepository.findLikeAndUnlikeByUserAndPost(user, post);
        ApiResponse apiResponse = null;

        if (user ==null){
            throw  new UserNotFoundException("user not found");
        }else if (post == null){
            throw new PostNotFoundException("post not found");
        }else if (likeAndUnlike.isPresent()){
            if (likeAndUnlike.get().getStatus().equals(LikeStatus.UNLIKE)){
             LikePost likeAgain =  likeAndUnlike.get();
             likeAgain.setStatus(LikeStatus.LIKE);
             likeRepository.save(likeAgain);
             apiResponse = new ApiResponse(Boolean.TRUE, "You like the post");
            }else {
                apiResponse = new ApiResponse(Boolean.TRUE, "You already like this post");
                return apiResponse;
            }
        }
        else {
            like.setPost(post);
            like.setUser(user);
            like.setStatus(LikeStatus.LIKE);
            like.setDateCreated(localDate);
            like.setLocalDateTime(localDateTime);
            like.setTimeCreated(localTime);
            apiResponse = new ApiResponse(Boolean.TRUE, "You like this post");
            likeRepository.save(like);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse unLikePost(Long postId, Long userId) {
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        ApiResponse apiResponse = null;
        Optional<LikePost> likeAndUnlike = likeRepository.findLikeAndUnlikeByUserAndPost(user, post);
        if (likeAndUnlike.isPresent()){
            LikePost unlike = likeAndUnlike.get();
            unlike.setStatus(LikeStatus.UNLIKE);
            likeRepository.save(unlike);
            apiResponse = new ApiResponse(Boolean.TRUE, "You unlike this post");
        }else {
            apiResponse = new ApiResponse(Boolean.TRUE, "You can't unlike more than once");
        }
        return apiResponse;
    }

    @Override
    public ApiResponse likeComment(Long commentId, Long userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        User user = userRepository.getById(userId);
        Comment comment = commentRepository.getById(commentId);
        Optional<LikeComment> likeComment = likeCommentRepository.findLikeCommentByUserAndComment(user,comment);
        ApiResponse apiResponse = null;
        LikeComment like = new LikeComment();
        if (user == null){
            throw new UserNotFoundException("user not found");
        }else if (comment == null){
            throw new RuntimeException("comment not found");
        }else if (likeComment.isPresent()){
            if (likeComment.get().getStatus().equals(LikeStatus.UNLIKE)){
                LikeComment likeAgain =  likeComment.get();
                likeAgain.setStatus(LikeStatus.LIKE);
                likeCommentRepository.save(likeAgain);
                apiResponse = new ApiResponse(Boolean.TRUE, "You like this comment");
            }else {
                apiResponse = new ApiResponse(Boolean.TRUE, "You already like this comment");
                return apiResponse;
            }
        }
        else {
            like.setComment(comment);
            like.setUser(user);
            like.setStatus(LikeStatus.LIKE);
            like.setDateCreated(localDate);
            like.setLocalDateTime(localDateTime);
            like.setTimeCreated(localTime);
            apiResponse = new ApiResponse(Boolean.TRUE, "You like this comment");
            likeCommentRepository.save(like);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse unlikeComment(Long commentId, Long userId) {
        User user = userRepository.getById(userId);
        Comment comment = commentRepository.getById(commentId);

        ApiResponse apiResponse = null;
        Optional<LikeComment> likeComment = likeCommentRepository.findLikeCommentByUserAndComment(user,comment);
        if (likeComment.isPresent()){
            LikeComment unlike = likeComment.get();
            unlike.setStatus(LikeStatus.UNLIKE);
            likeCommentRepository.save(unlike);
            apiResponse = new ApiResponse(Boolean.TRUE, "You unlike this comment");
        }else {
            apiResponse = new ApiResponse(Boolean.TRUE, "You can't unlike more than once");
        }
        return apiResponse;
    }
}
