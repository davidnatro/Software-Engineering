package ru.hse.kpokollok.service;

import java.util.List;
import ru.hse.kpokollok.data.model.FlightModel;
import ru.hse.kpokollok.data.model.PassengerModel;

public interface TicketService {

    PassengerModel addTicketToPassenger(Long flightId, Long passengerId);

    PassengerModel removeTicketFromPassenger(Long passengerId, Long flightId);

    List<FlightModel> findPassengerFlights(Long passengerId);
}
