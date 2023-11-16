package com.example.chatapp.domain.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String nickname;
    private String password;
    private String rePassword;
}
