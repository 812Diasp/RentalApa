package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    // Поиск отзывов по пользователю
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> findByUserId(@Param("userId") UUID userId);

    // Поиск отзывов по арендодателю
    @Query("SELECT r FROM Review r WHERE r.rentMan.id = :rentmanId")
    List<Review> findByRentManId(@Param("rentmanId") UUID rentmanId);

    // Поиск отзывов для конкретного пользователя и арендодателя
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.rentMan.id = :rentmanId")
    List<Review> findByUserAndRentMan(
            @Param("userId") UUID userId,
            @Param("rentmanId") UUID rentmanId);
}