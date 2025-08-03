package org.akidra.rentalapa.services;

import org.akidra.rentalapa.entities.RentMan;
import org.akidra.rentalapa.entities.RentObject;
import org.akidra.rentalapa.repositories.RentManRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RentManService {
    private final RentManRepository rentManRepository;

    public RentManService(RentManRepository rentManRepository) {
        this.rentManRepository = rentManRepository;
    }

    public List<RentMan> getAllRentMen() {
        return rentManRepository.findAll();
    }

    public RentMan getRentManById(UUID id) {
        return rentManRepository.findById(id).orElse(null);
    }

    public RentMan saveRentMan(RentMan rentMan) {
        return rentManRepository.save(rentMan);
    }

    public void deleteRentMan(UUID id) {
        rentManRepository.deleteById(id);
    }

    public List<RentObject> getRentObjectsByRentManId(UUID rentManId) {
        RentMan rentMan = getRentManById(rentManId);
        return rentMan != null ? rentMan.getRentObjects() : List.of();
    }
}