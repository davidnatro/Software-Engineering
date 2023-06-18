package ru.hse.kpokollok.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.kpokollok.data.entity.FlightEntity;
import ru.hse.kpokollok.data.entity.PassengerEntity;
import ru.hse.kpokollok.data.model.FlightModel;
import ru.hse.kpokollok.data.model.PassengerModel;
import ru.hse.kpokollok.exception.AlreadyExistsException;
import ru.hse.kpokollok.exception.NotFoundException;
import ru.hse.kpokollok.repository.FlightRepository;
import ru.hse.kpokollok.repository.PassengerRepository;
import ru.hse.kpokollok.service.TicketService;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public PassengerModel addTicketToPassenger(Long passengerId, Long flightId) {
        PassengerEntity passengerEntity = findPassengerIfExists(passengerId);
        FlightEntity flightEntity = findFLightIfExists(flightId);

        if (passengerEntity.getFlights().contains(flightEntity)) {
            throw new AlreadyExistsException("Passenger already bought ticket on this flight");
        }

        Set<FlightEntity> flightEntities = passengerEntity.getFlights();
        flightEntities.add(flightEntity);
        passengerRepository.save(passengerEntity);

        return PassengerModel.fromEntity(passengerEntity);
    }

    @Override
    @Transactional
    public PassengerModel removeTicketFromPassenger(Long passengerId, Long flightId) {
        PassengerEntity passengerEntity = findPassengerIfExists(passengerId);
        FlightEntity flightEntity = findFLightIfExists(flightId);

        if (!passengerEntity.getFlights().contains(flightEntity)) {
            throw new NotFoundException("Passenger didn't buy ticket on this flight");
        }

        passengerEntity.getFlights().remove(flightEntity);
        passengerRepository.save(passengerEntity);

        return PassengerModel.fromEntity(passengerEntity);
    }

    @Override
    @Transactional
    public List<FlightModel> findPassengerFlights(Long passengerId) {
        PassengerEntity passenger = findPassengerIfExists(passengerId);

        return FlightModel.fromEntities(passenger.getFlights());
    }

    private PassengerEntity findPassengerIfExists(Long passengerId) {
        Optional<PassengerEntity> passenger = passengerRepository.findById(passengerId);
        if (passenger.isEmpty()) {
            throw new NotFoundException("flight or passenger was not found");
        }

        return passenger.get();
    }

    private FlightEntity findFLightIfExists(Long flightId) {
        Optional<FlightEntity> flight = flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            throw new NotFoundException("flight or passenger was not found");
        }

        return flight.get();
    }
}
