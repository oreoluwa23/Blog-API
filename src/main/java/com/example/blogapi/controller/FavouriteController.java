package com.example.blogapi.controller;

import com.example.blogapi.model.Favorite;
import com.example.blogapi.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> displayAllUserFavourite(@PathVariable(name = "userId") Long userId){
        List<Favorite> favoriteList = favouriteService.displayAllFavouriteOfAUser(userId);
        return new ResponseEntity<>(favoriteList, HttpStatus.CREATED);
    }
}
