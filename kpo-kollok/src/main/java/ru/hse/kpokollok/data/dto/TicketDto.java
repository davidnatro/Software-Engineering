package ru.hse.kpokollok.data.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {

    @NotNull
    private Long passengerId;

    @NotNull
    private Long flightId;
}
