package com.example.blogapi.service;

import com.example.blogapi.model.Favorite;
import com.example.blogapi.payload.ApiResponse;

import java.util.List;

public interface FavouriteService {
    ApiResponse addFavourite(Long userId, Long postId);
    List<Favorite> displayAllFavouriteOfAUser(Long userId);
}
