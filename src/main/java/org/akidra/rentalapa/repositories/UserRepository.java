package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}