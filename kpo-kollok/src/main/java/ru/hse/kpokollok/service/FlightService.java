package ru.hse.kpokollok.service;

import java.time.LocalDateTime;
import java.util.List;
import ru.hse.kpokollok.data.model.FlightModel;

public interface FlightService {

    List<FlightModel> findAll();

    FlightModel create(String flightNumber, String from, String to, LocalDateTime depature,
                       LocalDateTime arrival);

    void deleteById(Long id);
}
