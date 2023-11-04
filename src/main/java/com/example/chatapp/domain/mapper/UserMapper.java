package com.example.chatapp.domain.mapper;

import com.example.chatapp.domain.model.CreateUserRequest;
import com.example.chatapp.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public final PasswordEncoder passwordEncoder;

    public User mapToUser(CreateUserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateRegistered(LocalDateTime.now())
                .build();
    }
}
