package com.example.authkpo.service.impl;

import com.example.authkpo.data.entity.UserEntity;
import com.example.authkpo.data.model.AccessTokenModel;
import com.example.authkpo.exception.InvalidCredentialsException;
import com.example.authkpo.exception.NotFoundException;
import com.example.authkpo.repository.UserRepository;
import com.example.authkpo.service.AuthService;
import com.example.authkpo.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessTokenModel authenticate(String username, String password) {
        if (!userRepository.existsByUsername(username)) {
            throw new NotFoundException("User with username '" + username + "' not found");
        }

        UserEntity user = userRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("invalid credentials");
        }

        return jwtService.generateJws(user);
    }
}
