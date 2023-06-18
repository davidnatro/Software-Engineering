package com.example.authkpo.controller;

import com.example.authkpo.data.dto.UserDto;
import com.example.authkpo.data.model.AccessTokenModel;
import com.example.authkpo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AccessTokenModel> authenticate(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                authService.authenticate(userDto.getUsername(), userDto.getPassword()));
    }
}
