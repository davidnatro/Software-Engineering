package com.example.authkpo.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    private String password;
}
