package com.example.blogapi.controller;

import com.example.blogapi.dto.LoginDto;
import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.model.Comment;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.service.CommentService;
import com.example.blogapi.service.FavouriteService;
import com.example.blogapi.service.LikeService;
import com.example.blogapi.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    FavouriteService favouriteService;
@GetMapping
    public ResponseEntity<List<User>> displayAllUsers(){
    List<User> userList = service.displayAllUsers();
    return new ResponseEntity<>(userList, HttpStatus.OK);
    }
@PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
    User newUser = service.createUser(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
@PostMapping("/login")
 public ResponseEntity<User> userLogin(@RequestBody LoginDto loginDto){
     User user = service.userLogIn(loginDto.getUserEmail(), loginDto.getUserPassword());
     if (user == null){
         throw new UserNotFoundException("User not found");
     }else
         return new ResponseEntity<>(user, HttpStatus.OK);
 }

 @PostMapping("/{userId}/posts/{postId}/comments")
    public ResponseEntity<Comment> addComment(@RequestParam String comment, @PathVariable(name = "postId") Long postId,
                                              @PathVariable(name = "userId") Long userId){
    Comment commentAdded = commentService.addComment(postId,comment, userId);
    return new ResponseEntity<>(commentAdded, HttpStatus.CREATED);
    }

@DeleteMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "postId") Long postId,
                                                     @PathVariable(name = "commentId") Long commentId){
        ApiResponse response = commentService.deleteComment(commentId,postId,userId);
        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

  @GetMapping("/{userId}")
  public ResponseEntity<List<Comment>> displayCommentByEachUser(@PathVariable(name = "userId") Long userId){
    List<Comment> comments = commentService.displayAllCommentByAUser(userId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
  @DeleteMapping("/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") Long userId){
    service.deleteUserById(userId);
    return new ResponseEntity<>("User disabled", HttpStatus.ACCEPTED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> restoreAUser(@PathVariable(name = "userId") Long userId){
    User user = service.restoreUserAccount(userId);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }
  @PutMapping("/{userId}/posts/{postId}/comments/{commentId}")
  public ResponseEntity<Comment> updateComment(@PathVariable("userId") Long userId,
                                               @PathVariable(name = "postId") Long postId,
                                               @PathVariable(name = "commentId") Long commentId,
                                               @RequestParam(name = "commentBody") String commentBody){
    Comment comment = commentService.updateComment(postId, commentBody, userId, commentId);
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }
@PostMapping("/{userId}/posts/{postId}/like")
  public ResponseEntity<ApiResponse> likePostByAUser(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "postId") Long postId){
    ApiResponse response = likeService.likePost(postId, userId);
    HttpStatus status = response.getSuccess() ? HttpStatus.CREATED : HttpStatus.NO_CONTENT;
    return new ResponseEntity<>(response, status);
  }

  @PostMapping("/{userId}/posts/{postId}/unlike")
  public ResponseEntity<ApiResponse> unLikePostByAUser(@PathVariable(name = "userId") Long userId,
                                                       @PathVariable(name = "postId") Long postId){
      ApiResponse response = likeService.unLikePost(postId, userId);
      HttpStatus status = response.getSuccess() ? HttpStatus.CREATED : HttpStatus.NO_CONTENT;
      return new ResponseEntity<>(response, status);
  }
  @PostMapping({"/{userId}/posts/{postId}/favorite"})
  public ResponseEntity<ApiResponse> addFavorites(@PathVariable(name = "userId") Long userId,
                                                  @PathVariable(name = "postId") Long postId){
    ApiResponse response = favouriteService.addFavourite(userId,postId);
    HttpStatus status = response.getSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(response, status);
  }

}
