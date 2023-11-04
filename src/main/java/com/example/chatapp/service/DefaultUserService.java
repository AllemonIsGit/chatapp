package com.example.chatapp.service;

import com.example.chatapp.domain.exception.UserDoesNotExists;
import com.example.chatapp.domain.exception.UsernameAlreadyExistsException;
import com.example.chatapp.domain.mapper.UserMapper;
import com.example.chatapp.domain.model.RegisterDto;
import com.example.chatapp.domain.model.User;
import com.example.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(RegisterDto request) {
        if (existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("This username is taken.");
        }

        return userRepository.save(userMapper.mapToUser(request));
    }

    @Override
    public User update(User user) {
        if (!existsByUsername(user.getUsername())) {
            throw new UserDoesNotExists("User doesn't exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
