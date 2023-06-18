package com.example.authkpo.service.impl;

import com.example.authkpo.data.entity.UserEntity;
import com.example.authkpo.data.model.UserModel;
import com.example.authkpo.exception.AlreadyExistsException;
import com.example.authkpo.exception.InvalidCredentialsException;
import com.example.authkpo.exception.NotFoundException;
import com.example.authkpo.repository.RoleRepository;
import com.example.authkpo.repository.UserRepository;
import com.example.authkpo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel create(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyExistsException(
                    "User with username '" + username + "' already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));

        return UserModel.of(userRepository.save(userRepository.save(user)));
    }

    @Override
    public UserModel update(String username, String password) {
        if (!userRepository.existsByUsername(username)) {
            throw new NotFoundException("User with username '" + username + "' not found");
        }

        UserEntity user = userRepository.findByUsername(username);
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException("invalid credentials");
        }

        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));

        return UserModel.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserModel addRole(String username, String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            throw new NotFoundException("Role with name '" + roleName + "' not found");
        }

        if (!userRepository.existsByUsername(username)) {
            throw new NotFoundException("User with username '" + username + "' not found");
        }

        UserEntity user = userRepository.findByUsername(username);
        user.getRoles().add(roleRepository.findByName(roleName));

        return UserModel.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserModel removeRole(String username, String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            throw new NotFoundException("Role with name '" + roleName + "' not found");
        }

        if (!userRepository.existsByUsername(username)) {
            throw new NotFoundException("User with username '" + username + "' not found");
        }

        UserEntity user = userRepository.findByUsername(username);
        user.getRoles().remove(roleRepository.findByName(roleName));

        return UserModel.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new NotFoundException("User with username '" + username + "' not found");
        }

        userRepository.deleteByUsername(username);
    }
}
