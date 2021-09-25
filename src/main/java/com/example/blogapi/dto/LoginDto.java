package com.example.blogapi.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String userEmail;
    private String userPassword;
}
