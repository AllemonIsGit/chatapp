package com.example.chatapp.service;

import com.example.chatapp.domain.model.CreateUserRequest;
import com.example.chatapp.domain.model.User;

import java.util.Optional;

public interface UserService {
    User create(CreateUserRequest request);
    User update(User user);
    Optional<User> getById(Long id);
}
