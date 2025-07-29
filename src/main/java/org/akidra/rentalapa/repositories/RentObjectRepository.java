package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.RentObject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RentObjectRepository extends JpaRepository<RentObject, UUID> {
}