package ru.hse.kpokollok.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.kpokollok.data.model.PassengerModel;
import ru.hse.kpokollok.service.PassengerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerModel>> findAll() {
        return ResponseEntity.ok(passengerService.findAll());
    }

    @PostMapping("{fullname}")
    public ResponseEntity<PassengerModel> createPassenger(@PathVariable String fullname) {
        return ResponseEntity.ok(passengerService.create(fullname));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePassender(@PathVariable Long id) {
        passengerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
