package com.example.blogapi.controller;

import com.example.blogapi.model.Post;
import com.example.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;
@GetMapping
    public ResponseEntity<List<Post>> displayAllUserPost(){
        List<Post> posts = postService.displayAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
@PostMapping("/{userId}")
    public ResponseEntity<String> createNewPost(@RequestParam String postBody, @PathVariable(name = "userId") long userId){
   postService.createPost(postBody, userId);
    return new ResponseEntity<>("Post Created", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPost(@PathVariable(name = "userId") long userId){
    List<Post> posts = postService.displayAUserPost(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
