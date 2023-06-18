package com.example.authkpo.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    @NonNull
    @NotBlank
    private String name;
}
