package com.example.blogapi.service.impl;

import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.model.Connection;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.repository.ConnectionRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @Override
    public ApiResponse addConnection(Long userConnectorId, Long userConnectionId) {
        User userConnector = userRepository.getById(userConnectorId);
        User userConnection = userRepository.getById(userConnectionId);
        if (userConnection == null || userConnector == null){
            throw new UserNotFoundException("user not found");
        }
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        ApiResponse response = null;
        Optional<Connection> optionalConnection = connectionRepository.findConnectionByConnectorAndConnection(userConnector,userConnection);
        if (optionalConnection.isPresent() && optionalConnection.get().getConnector().equals(userConnector)){
            response = new ApiResponse(Boolean.TRUE, "You have already added this user to your connection");
        }else {
            Connection connection = new Connection();
            connection.setConnection(userConnection);
            connection.setConnector(userConnector);
            connection.setDateCreated(localDate);
            connection.setTimeCreated(localTime);
            connection.setLocalDateTime(localDateTime);
            connectionRepository.save(connection);
            response = new ApiResponse(Boolean.TRUE, "User added to your connection");
        }


        return response;
    }

    @Override
    public List<Post> getAllThePostByAUserConnection(Long userConnectorId, Long userId) {
        User user = userRepository.getById(userConnectorId);
        User userConnector = userRepository.getById(userId);
        Optional<Connection> connection = connectionRepository.findConnectionByConnectorAndConnection(userConnector, user);
        List<Post> posts = null;
        if (connection.isPresent()){
            if (connection.get().getConnector().equals(userConnector)){
                User userConnection = connection.get().getConnection();
                posts = postRepository.findByUser(userConnection);
            }else {
                throw  new UserNotFoundException("Not Allowed");
            }
        }
        return posts;
    }
}
