package org.akidra.rentalapa.controllers;

import org.akidra.rentalapa.entities.RentObject;
import org.akidra.rentalapa.services.RentObjectService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentobjects")
public class RentObjectController {
    private final RentObjectService rentObjectService;

    public RentObjectController(RentObjectService rentObjectService) {
        this.rentObjectService = rentObjectService;
    }

    @GetMapping
    public List<RentObject> getAll() {
        return rentObjectService.getAllRentObjects();
    }

    @GetMapping("/{id}")
    public RentObject getById(@PathVariable UUID id) {
        return rentObjectService.getRentObjectById(id);
    }

    @PostMapping
    public RentObject create(@RequestBody RentObject rentObject) {
        return rentObjectService.saveRentObject(rentObject);
    }

    @PutMapping("/{id}")
    public RentObject update(@PathVariable UUID id, @RequestBody RentObject rentObject) {
        rentObject.setId(id);
        return rentObjectService.saveRentObject(rentObject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        rentObjectService.deleteRentObject(id);
    }

    @PostMapping("/{id}/reserve")
    public RentObject reservePeriod(
            @PathVariable UUID id,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return rentObjectService.reservePeriod(id, startDate, endDate);
    }

    @GetMapping("/search")
    public List<RentObject> search(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating) {
        return rentObjectService.searchRentObjects(city, country, startDate, endDate, minPrice, maxPrice, minRating);
    }
}