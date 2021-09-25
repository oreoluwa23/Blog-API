package com.example.blogapi.service;

import com.example.blogapi.model.User;
import com.example.blogapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("tests")
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;


    @Autowired
    private ObjectMapper objectMapper;

    private List<User>  userList;

    private String uri = "/api/v1/users";

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveUserSuccessfully(){

   }


}