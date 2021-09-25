package com.example.blogapi.repository;

import com.example.blogapi.model.Comment;
import com.example.blogapi.model.LikeComment;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findLikeCommentByUserAndComment(User user, Comment comment);
}
