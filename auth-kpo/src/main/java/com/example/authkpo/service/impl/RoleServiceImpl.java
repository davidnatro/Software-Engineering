package com.example.authkpo.service.impl;

import com.example.authkpo.data.entity.RoleEntity;
import com.example.authkpo.data.model.RoleModel;
import com.example.authkpo.exception.AlreadyExistsException;
import com.example.authkpo.exception.NotFoundException;
import com.example.authkpo.repository.RoleRepository;
import com.example.authkpo.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleModel> findAll() {
        return RoleModel.of(roleRepository.findAll());
    }

    @Override
    public RoleModel create(String name) {
        if (roleRepository.existsByName(name)) {
            throw new AlreadyExistsException("Role with name '" + name + "' already exists");
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);

        return RoleModel.of(roleRepository.save(roleEntity));
    }

    @Override
    @Transactional
    public void delete(String name) {
        if (!roleRepository.existsByName(name)) {
            throw new NotFoundException("Role with name '" + name + "' not found");
        }

        roleRepository.delete(roleRepository.findByName(name));
    }
}
