package com.example.chatapp.service;

import com.example.chatapp.domain.exception.UserDoesNotExistsException;
import com.example.chatapp.domain.model.User;
import com.example.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User update(User user) {
        if (!existsByUsername(user.getUsername())) {
            throw new UserDoesNotExistsException("User doesn't exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found in database.")
        );
    }
}
