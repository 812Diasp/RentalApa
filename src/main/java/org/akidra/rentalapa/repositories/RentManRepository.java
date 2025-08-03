package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.RentMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

public interface RentManRepository extends JpaRepository<RentMan, UUID> {

    // Обновление рейтинга арендодателя
    @Transactional
    @Modifying
    @Query("UPDATE RentMan rm SET rm.rentorRating = :newRating WHERE rm.id = :rentmanId")
    void updateRentmanRating(@Param("rentmanId") UUID rentmanId, @Param("newRating") Double newRating);
}