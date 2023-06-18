package ru.hse.kpokollok.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.kpokollok.data.dto.TicketDto;
import ru.hse.kpokollok.data.model.FlightModel;
import ru.hse.kpokollok.data.model.PassengerModel;
import ru.hse.kpokollok.service.TicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<PassengerModel> buyTicket(@Valid @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.addTicketToPassenger(ticketDto.getPassengerId(),
                                                                    ticketDto.getFlightId()));
    }

    @GetMapping("{passengerId}")
    public ResponseEntity<List<FlightModel>> getPassengerTickets(@PathVariable Long passengerId) {
        return ResponseEntity.ok(ticketService.findPassengerFlights(passengerId));
    }

    @DeleteMapping
    public ResponseEntity<PassengerModel> returnTicket(@Valid @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.removeTicketFromPassenger(ticketDto.getPassengerId(),
                                                                         ticketDto.getFlightId()));
    }
}
