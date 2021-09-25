package com.example.blogapi.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String name;
    private String password;
    private String email;
}
