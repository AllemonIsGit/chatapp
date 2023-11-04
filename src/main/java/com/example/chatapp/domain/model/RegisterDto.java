package com.example.chatapp.domain.model;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String nickname;
    private String password;
    private String rePassword;
}
