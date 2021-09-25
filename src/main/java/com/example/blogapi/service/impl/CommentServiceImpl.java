package com.example.blogapi.service.impl;

import com.example.blogapi.exception.ApiException;
import com.example.blogapi.exception.CommentNotFoundException;
import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.model.Comment;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private static final String COMMENT_DOES_NOT_BELONG_TO_POST = "Comment does not belong to post";
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(Long postId, String commentBody, Long userId) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Comment comment = new Comment();
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);
        comment.setLocalDateTime(localDateTime);
        comment.setTimeCreated(localTime);
        comment.setDateCreated(localDate);
        comment.setCommentBody(commentBody);
        comment.setPost(post);
        comment.setUser(user);
        Comment commentCreated = commentRepository.save(comment);

        return commentCreated;
    }

    @Override
    public List<Comment> displayAllCommentByAUser(Long userId) {
        User user = userRepository.getById(userId);
        return commentRepository.findAllByUser(user);
    }

    @Override
    public List<Comment> getPost(Long postId) {
        Post post = postRepository.getById(postId);
        return commentRepository.findAllByPost(post);
    }

    @Override
    public ApiResponse deleteComment(Long commentId, Long userId, Long postId) {
        Post post = postRepository.getById(postId);
        Comment comment = commentRepository.getById(commentId);
        ApiResponse apiResponse = null;
        if (!comment.getPost().getId().equals(post.getId())){
            apiResponse = new ApiResponse(Boolean.FALSE, COMMENT_DOES_NOT_BELONG_TO_POST);
        }
       if (comment.getUser().getId().equals(userId)) {
            apiResponse = new ApiResponse(Boolean.TRUE, "You successfully delete the comment");
        }
        return apiResponse;
    }

    @Override
    public Comment getComment(Long postId, Long commentId) {
        Post post = postRepository.getById(postId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.get();
    }

    @Override
    public Comment updateComment(Long postId, String commentBody, Long userId, Long commentId) {
    Post post = postRepository.getById(postId);
    Comment comment = commentRepository.getById(commentId);

    if (!comment.getPost().getId().equals(post.getId())){
        throw new PostNotFoundException("it does not belong to tha same post");
    }

    comment.setCommentBody(commentBody);
    return comment;

    }
}
