package com.example.blogapi.service;

import com.example.blogapi.dto.UserRegistrationDto;
import com.example.blogapi.model.User;

import java.util.List;

public interface UserService {
    User userLogIn(String email, String password);
    User createUser(User user);
    List<User> displayAllUsers();
    void deleteUserById(Long userId);
    void removeUserBySchedule() throws InterruptedException;
    User restoreUserAccount(Long userId);
}
