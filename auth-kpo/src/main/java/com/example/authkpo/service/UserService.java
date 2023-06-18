package com.example.authkpo.service;

import com.example.authkpo.data.model.UserModel;

public interface UserService {

    UserModel create(String username, String password);

    UserModel update(String username, String password);

    UserModel addRole(String username, String roleName);

    UserModel removeRole(String username, String roleName);

    void delete(String username);
}
