package com.example.authkpo.controller;

import com.example.authkpo.data.dto.RoleDto;
import com.example.authkpo.data.model.RoleModel;
import com.example.authkpo.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleModel>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<RoleModel> createRole(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.create(roleDto.getName()));
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteRole(@PathVariable String name) {
        roleService.delete(name);
        return ResponseEntity.ok().build();
    }
}
