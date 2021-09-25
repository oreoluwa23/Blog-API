package com.example.blogapi.service;

import com.example.blogapi.payload.ApiResponse;

public interface LikeService {
    ApiResponse likePost(Long postId, Long userId);
    ApiResponse unLikePost(Long postId, Long userId);
    ApiResponse likeComment(Long commentId, Long userId);
    ApiResponse unlikeComment(Long commentId, Long userId);
}
