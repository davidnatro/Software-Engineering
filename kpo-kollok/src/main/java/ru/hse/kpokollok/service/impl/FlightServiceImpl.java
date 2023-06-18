package ru.hse.kpokollok.service.impl;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.kpokollok.data.entity.FlightEntity;
import ru.hse.kpokollok.data.model.FlightModel;
import ru.hse.kpokollok.exception.AlreadyExistsException;
import ru.hse.kpokollok.exception.NotFoundException;
import ru.hse.kpokollok.repository.FlightRepository;
import ru.hse.kpokollok.service.FlightService;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<FlightModel> findAll() {
        return FlightModel.fromEntities(flightRepository.findAll());
    }

    @Override
    public FlightModel create(String flightNumber, String from, String to,
                              LocalDateTime depatureTime, LocalDateTime arrivalTime) {

        if (flightRepository.existsByFlightNumber(flightNumber)) {
            throw new AlreadyExistsException(
                    "Flight with flight number '" + flightNumber + "' already exists");
        }

        FlightEntity flight = new FlightEntity();
        flight.setFlightNumber(flightNumber);
        flight.setFrom(from);
        flight.setTo(to);
        flight.setDepatureTime(depatureTime);
        flight.setArrivalTime(arrivalTime);

        return FlightModel.fromEntity(flightRepository.save(flight));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new NotFoundException("Flight with id '" + id + "' not found");
        }

        flightRepository.deleteById(id);
    }
}
