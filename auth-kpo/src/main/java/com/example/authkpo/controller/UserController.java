package com.example.authkpo.controller;

import com.example.authkpo.data.dto.UserDto;
import com.example.authkpo.data.dto.UserRoleDto;
import com.example.authkpo.data.model.UserModel;
import com.example.authkpo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto.getUsername(), userDto.getPassword()));
    }

    @PutMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto.getUsername(), userDto.getPassword()));
    }

    @PutMapping("add-role")
    public ResponseEntity<UserModel> addRoleToUser(@Valid @RequestBody UserRoleDto userRoleDto) {
        return ResponseEntity.ok(
                userService.addRole(userRoleDto.getUsername(), userRoleDto.getRoleName()));
    }

    @PutMapping("remove-role")
    public ResponseEntity<UserModel> removeRoleFromUser(
            @Valid @RequestBody UserRoleDto userRoleDto) {
        return ResponseEntity.ok(
                userService.removeRole(userRoleDto.getUsername(), userRoleDto.getRoleName()));
    }

    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable String username) {
        userService.delete(username);
    }
}
