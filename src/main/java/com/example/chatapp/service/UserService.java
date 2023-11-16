package com.example.chatapp.service;

import com.example.chatapp.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User update(User user);
    Optional<User> getById(Long id);
}
