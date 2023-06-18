package com.example.authkpo.service.impl;

import com.example.authkpo.configuration.property.JwtProperties;
import com.example.authkpo.data.entity.UserEntity;
import com.example.authkpo.data.model.AccessTokenModel;
import com.example.authkpo.service.JwtService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public AccessTokenModel generateJws(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("roles", user.getRoles());

        Date expiration = new Date();
        JwtBuilder jws = Jwts.builder().setClaims(claims)
                             .setExpiration(new Date(expiration.getTime() + 300000))
                             .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey()));

        return new AccessTokenModel(jws.compact(), Long.toString(expiration.getTime() + 300000));
    }
}
