package ru.hse.kpokollok.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto {

    @NotNull
    @NotBlank
    private String flightNumber;

    @NotNull
    @NotBlank
    private String from;

    @NotNull
    @NotBlank
    private String to;
}
