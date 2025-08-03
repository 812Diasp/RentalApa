package org.akidra.rentalapa.repositories;

import org.akidra.rentalapa.entities.RentObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface RentObjectRepository extends JpaRepository<RentObject, UUID> {

    @Query("SELECT ro FROM RentObject ro " +
            "WHERE (:city IS NULL OR LOWER(ro.address) LIKE LOWER(CONCAT('%', :city, '%'))) " +
            "AND (:country IS NULL OR LOWER(ro.address) LIKE LOWER(CONCAT('%', :country, '%'))) " +
            "AND (:minPrice IS NULL OR ro.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR ro.price <= :maxPrice) " +
            "AND (:minRating IS NULL OR ro.rentMan.rentorRating >= :minRating)")
    List<RentObject> findByFilters(
            @Param("city") String city,
            @Param("country") String country,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minRating") Double minRating);

    List<RentObject> findByRentManId(UUID rentManId);
}