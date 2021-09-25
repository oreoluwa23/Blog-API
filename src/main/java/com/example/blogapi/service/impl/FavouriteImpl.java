package com.example.blogapi.service.impl;

import com.example.blogapi.exception.UserNotFoundException;
import com.example.blogapi.model.Favorite;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.repository.FavoriteRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteImpl implements FavouriteService {
@Autowired
    UserRepository userRepository;
@Autowired
    PostRepository postRepository;
@Autowired
    FavoriteRepository favoriteRepository;
    @Override
    public ApiResponse addFavourite(Long userId, Long postId) {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);
        ApiResponse response = null;
        Optional<Favorite> optionalFavorite = favoriteRepository.findFavoriteByPostAndUser(post, user);
        if (optionalFavorite.isPresent()){
            response = new ApiResponse(Boolean.TRUE, "You have already added this post");
        }else {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setDateCreated(localDate);
            favorite.setTimeCreated(localTime);
            favorite.setLocalDateTime(localDateTime);
            favorite.setPost(post);
            response = new ApiResponse(Boolean.TRUE, "You have added a new post as favourite");
            favoriteRepository.save(favorite);
        }
        return response;
    }

    @Override
    public List<Favorite> displayAllFavouriteOfAUser(Long userId) {
        User user = userRepository.getById(userId);
        List<Favorite> favorites;
        if (user == null){
            throw new UserNotFoundException("user not found");
        }else {
            favorites = favoriteRepository.findByUser(user);
        }

        return favorites;
    }
}
