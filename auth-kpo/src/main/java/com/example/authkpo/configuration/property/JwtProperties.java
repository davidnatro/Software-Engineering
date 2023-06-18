package com.example.authkpo.configuration.property;

import io.jsonwebtoken.io.Decoders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private byte[] secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = Decoders.BASE64.decode(secretKey);
    }
}
