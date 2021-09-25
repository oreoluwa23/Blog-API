package com.example.blogapi.repository;

import com.example.blogapi.enums.UserAccountStatus;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);
    List<User> findUserByStatus(UserAccountStatus status);
}
