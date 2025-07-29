package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.RentMan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RentManRepository extends JpaRepository<RentMan, UUID> {
}