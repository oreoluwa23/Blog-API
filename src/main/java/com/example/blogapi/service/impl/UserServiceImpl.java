package com.example.blogapi.service.impl;

import com.example.blogapi.dto.UserRegistrationDto;
import com.example.blogapi.enums.UserAccountStatus;
import com.example.blogapi.model.User;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User userLogIn(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public User createUser(User user) {
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        LocalDateTime localDateTime= LocalDateTime.now();
        user.setEmail(user.getEmail());
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        user.setDateCreated(localDate);
        user.setTimeCreated(localTime);
        user.setLocalDateTime(localDateTime);
        user.setStatus(UserAccountStatus.ACTIVE);
      return   userRepository.save(user);
    }

    @Override
    public List<User> displayAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long userId) {
        User user = userRepository.getById(userId);
        user.setStatus(UserAccountStatus.DISABLED);
        userRepository.save(user);
    }
@Scheduled(fixedDelay = 100000)
    @Override
    public void removeUserBySchedule() throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        List<User> users = userRepository.findUserByStatus(UserAccountStatus.DISABLED);
        userRepository.deleteAll(users);
    }

    @Override
    public User restoreUserAccount(Long userId) {
        User user = userRepository.getById(userId);
        if (user.getStatus().equals(UserAccountStatus.DISABLED)){
            user.setStatus(UserAccountStatus.ACTIVE);
            userRepository.save(user);
        }
        return user;
    }
}
