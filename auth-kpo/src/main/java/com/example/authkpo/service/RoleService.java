package com.example.authkpo.service;

import com.example.authkpo.data.model.RoleModel;
import java.util.List;

public interface RoleService {

    List<RoleModel> findAll();

    RoleModel create(String name);

    void delete(String name);
}
