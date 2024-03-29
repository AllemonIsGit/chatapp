package com.example.chatapp.service;

import com.example.chatapp.domain.dto.request.CreateUserRequest;
import com.example.chatapp.domain.dto.request.LoginRequest;
import com.example.chatapp.domain.dto.response.AuthenticationResponse;
import com.example.chatapp.domain.exception.PasswordDoNotMatchException;
import com.example.chatapp.domain.model.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.config.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var jwtToken = jwtUtil.createToken(request.getUsername());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse register(CreateUserRequest request) {
        if (!checkPasswordMatch(request.getPassword(), request.getRePassword())) {
            throw new PasswordDoNotMatchException("Password do not match.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .dateRegistered(LocalDateTime.now())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtUtil.createToken(user.getUsername());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private boolean checkPasswordMatch(String password, String rePassword) {
        return Objects.equals(password, rePassword);
    }
}
