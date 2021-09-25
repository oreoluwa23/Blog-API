package com.example.blogapi.repository;

import com.example.blogapi.model.LikePost;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikePost, Long> {
    Optional<LikePost> findLikeAndUnlikeByUserAndPost(User user, Post post);
}
