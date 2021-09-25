package com.example.blogapi.controller;

import com.example.blogapi.model.Comment;
import com.example.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping
    public ResponseEntity<List<Comment>> displayAllComments(@PathVariable(name = "postId") Long postId){
        List<Comment> comments = commentService.getPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId){
        Comment comment = commentService.getComment(postId, commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
