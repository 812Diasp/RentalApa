package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Поиск по email
    Optional<User> findByEmail(String email);

    // Поиск по username
    Optional<User> findByUsername(String username);

    // Обновление рейтинга пользователя
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.rating = :newRating WHERE u.id = :userId")
    void updateUserRating(@Param("userId") UUID userId, @Param("newRating") Double newRating);

    // Обновление статуса блокировки
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.blocked = :blocked WHERE u.id = :userId")
    void updateUserBlockStatus(@Param("userId") UUID userId, @Param("blocked") boolean blocked);
}