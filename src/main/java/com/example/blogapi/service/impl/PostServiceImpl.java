package com.example.blogapi.service.impl;

import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public void createPost(String postBody, long id) {
        Post post = new Post();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        User user = userRepository.getById(id);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        post.setDateCreated(localDate);
        post.setTimeCreated(localTime);
        post.setLocalDateTime(localDateTime);
        post.setPostBody(postBody);
        post.setUser(user);
        postRepository.save(post);

    }

    @Cacheable(cacheNames = "posts")
    @Override
    public List<Post> displayAllPost() {
        return postRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "user_cache")
    public List<Post> displayAUserPost(long id){
        User user = userRepository.getById(id);
        System.out.println(user.getId());
     return postRepository.findByUser(user);
     }
}
