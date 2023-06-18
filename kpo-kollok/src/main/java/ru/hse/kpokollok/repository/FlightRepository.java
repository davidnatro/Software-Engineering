package ru.hse.kpokollok.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.kpokollok.data.entity.FlightEntity;

@Repository
public interface FlightRepository extends CrudRepository<FlightEntity, Long> {

    boolean existsByFlightNumber(String flightNumber);
}
