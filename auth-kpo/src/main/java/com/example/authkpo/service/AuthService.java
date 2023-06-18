package com.example.authkpo.service;

import com.example.authkpo.data.model.AccessTokenModel;

public interface AuthService {

    AccessTokenModel authenticate(String username, String password);
}
