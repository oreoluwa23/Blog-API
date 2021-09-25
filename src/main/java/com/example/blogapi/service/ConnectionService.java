package com.example.blogapi.service;

import com.example.blogapi.model.Connection;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.ApiResponse;

import java.util.List;

public interface ConnectionService {
    ApiResponse addConnection(Long userConnectorId, Long userConnectionId);
    List<Post> getAllThePostByAUserConnection(Long userId, Long userConnectionId);
}
