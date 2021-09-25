package com.example.blogapi.service;

import com.example.blogapi.model.Post;

import java.util.List;

public interface PostService {
    void createPost(String postBody, long id);
    List<Post> displayAllPost();
     List<Post> displayAUserPost(long id);
}
