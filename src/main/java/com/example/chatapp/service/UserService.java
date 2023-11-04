package com.example.chatapp.service;

import com.example.chatapp.domain.model.RegisterDto;
import com.example.chatapp.domain.model.User;

import java.util.Optional;

public interface UserService {
    User create(RegisterDto request);
    User update(User user);
    Optional<User> getById(Long id);
}
