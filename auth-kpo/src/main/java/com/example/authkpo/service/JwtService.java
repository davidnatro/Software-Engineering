package com.example.authkpo.service;

import com.example.authkpo.data.entity.UserEntity;
import com.example.authkpo.data.model.AccessTokenModel;

public interface JwtService {

    AccessTokenModel generateJws(UserEntity user);
}
