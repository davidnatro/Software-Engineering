package ru.hse.kpokollok.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.kpokollok.data.entity.PassengerEntity;
import ru.hse.kpokollok.data.model.PassengerModel;
import ru.hse.kpokollok.exception.AlreadyExistsException;
import ru.hse.kpokollok.exception.NotFoundException;
import ru.hse.kpokollok.repository.PassengerRepository;
import ru.hse.kpokollok.service.PassengerService;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerModel> findAll() {
        return PassengerModel.fromEntities(passengerRepository.findAll());
    }

    @Override
    public PassengerModel create(String fullName) {
        if (passengerRepository.existsByFullName(fullName)) {
            throw new AlreadyExistsException(
                    "Passenger with fullname '" + fullName + "' already exists");
        }

        PassengerEntity passenger = new PassengerEntity();
        passenger.setFullName(fullName);

        return PassengerModel.fromEntity(passengerRepository.save(passenger));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new NotFoundException("Passenger with id ' " + id + "' not found");
        }

        passengerRepository.deleteById(id);
    }
}
