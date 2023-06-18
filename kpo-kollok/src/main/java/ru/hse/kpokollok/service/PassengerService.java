package ru.hse.kpokollok.service;

import java.util.List;
import ru.hse.kpokollok.data.model.PassengerModel;

public interface PassengerService {

    List<PassengerModel> findAll();

    PassengerModel create(String fullName);

    void deleteById(Long id);
}
