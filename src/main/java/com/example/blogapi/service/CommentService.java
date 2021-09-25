package com.example.blogapi.service;

import com.example.blogapi.model.Comment;
import com.example.blogapi.payload.ApiResponse;

import java.util.List;

public interface CommentService {
    Comment addComment(Long postId, String commentBody, Long userId);
    List<Comment> displayAllCommentByAUser(Long userId);
    List<Comment> getPost(Long postId);
    ApiResponse deleteComment(Long commentId, Long postId, Long userId);
    Comment getComment(Long postId, Long commentId);
    Comment updateComment(Long postId, String commentBody, Long userId, Long commentId);
}
