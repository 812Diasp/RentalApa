package org.akidra.rentalapa.controllers;

import org.akidra.rentalapa.entities.RentMan;
import org.akidra.rentalapa.entities.RentObject; // Добавлен импорт
import org.akidra.rentalapa.services.RentManService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentmen")
public class RentManController {
    private final RentManService rentManService;

    public RentManController(RentManService rentManService) {
        this.rentManService = rentManService;
    }

    @GetMapping
    public List<RentMan> getAll() {
        return rentManService.getAllRentMen();
    }

    @GetMapping("/{id}")
    public RentMan getById(@PathVariable UUID id) {
        return rentManService.getRentManById(id);
    }

    @PostMapping
    public RentMan create(@RequestBody RentMan rentMan) {
        return rentManService.saveRentMan(rentMan);
    }

    @PutMapping("/{id}")
    public RentMan update(@PathVariable UUID id, @RequestBody RentMan rentMan) {
        rentMan.setId(id);
        return rentManService.saveRentMan(rentMan);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        rentManService.deleteRentMan(id);
    }

    @GetMapping("/{id}/objects")
    public List<RentObject> getRentObjects(@PathVariable UUID id) {
        return rentManService.getRentObjectsByRentManId(id);
    }
}