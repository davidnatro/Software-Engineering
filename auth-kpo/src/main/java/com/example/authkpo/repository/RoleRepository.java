package com.example.authkpo.repository;

import com.example.authkpo.data.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    boolean existsByName(String name);
}
