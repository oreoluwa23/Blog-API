package com.example.blogapi.repository;

import com.example.blogapi.model.Favorite;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findFavoriteByPostAndUser(Post post, User user);
}
