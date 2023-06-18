package ru.hse.kpokollok.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.kpokollok.data.entity.PassengerEntity;

@Repository
public interface PassengerRepository extends CrudRepository<PassengerEntity, Long> {

    boolean existsByFullName(String fullName);
}
