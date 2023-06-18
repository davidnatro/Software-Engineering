package ru.hse.kpokollok.controller;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.kpokollok.data.dto.FlightDto;
import ru.hse.kpokollok.data.model.FlightModel;
import ru.hse.kpokollok.service.FlightService;

@RestController
@RequiredArgsConstructor
@RequestMapping("flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightModel>> findAll() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @PostMapping
    public ResponseEntity<FlightModel> createFlight(@Valid @RequestBody FlightDto flightDto,
                                                    @RequestParam("depature") @DateTimeFormat(
                                                            pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime depature,
                                                    @RequestParam("arrival") @DateTimeFormat(
                                                            pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime arrival) {
        return ResponseEntity.ok(
                flightService.create(flightDto.getFlightNumber(), flightDto.getFrom(),
                                     flightDto.getTo(), depature, arrival));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
