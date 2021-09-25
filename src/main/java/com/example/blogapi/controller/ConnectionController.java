package com.example.blogapi.controller;

import com.example.blogapi.model.Post;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {
    @Autowired
    ConnectionService connectionService;

    @PostMapping("/users/{userId}/{userConnectionId}")
    public ResponseEntity<ApiResponse>  addConnection(@PathVariable(name = "userId") Long userConnectorId,
                                                    @PathVariable(name = "userConnectionId") Long userConnectionId){
       ApiResponse response = connectionService.addConnection(userConnectorId, userConnectionId);
        HttpStatus status = response.getSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response,status);
    }

    @GetMapping("users/{userId}/{userConnectionId}")
    public ResponseEntity<List<Post>> displayEachUserConnectionPost(@PathVariable(name = "userConnectionId") Long userConnectionId,
                                                                    @PathVariable(name = "userId") Long userId){
        List<Post> allPostsByAUser = connectionService.getAllThePostByAUserConnection(userConnectionId, userId);
        return new ResponseEntity<>(allPostsByAUser, HttpStatus.OK);
    }

}
