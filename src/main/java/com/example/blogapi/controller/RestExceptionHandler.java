package com.example.blogapi.controller;

import com.example.blogapi.exception.ApiException;
import com.example.blogapi.exception.CommentNotFoundException;
import com.example.blogapi.exception.PostNotFoundException;
import com.example.blogapi.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class RestExceptionHandler  {

    private ResponseEntity<Object> buildResponseEntity(ApiException apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex){
        ApiException apiError= new ApiException();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Check email address or password");

        return buildResponseEntity(apiError);

    }


    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<Object> handlePostNotFound(PostNotFoundException ex){
        ApiException apiError= new ApiException();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Empty posts not allowed");

        return buildResponseEntity(apiError);

    }

    @ExceptionHandler(CommentNotFoundException.class)
    protected ResponseEntity<Object> handleCommentNotFound(PostNotFoundException ex){
        ApiException apiError= new ApiException();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Comment not found");

        return buildResponseEntity(apiError);

    }
}
