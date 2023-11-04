package com.example.chatapp.domain.mapper;

import com.example.chatapp.domain.model.RegisterDto;
import com.example.chatapp.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User mapToUser(RegisterDto request) {
        return User.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .dateRegistered(LocalDateTime.now())
                .build();
    }
}
