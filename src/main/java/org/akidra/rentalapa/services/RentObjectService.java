package org.akidra.rentalapa.services;

import org.akidra.rentalapa.entities.RentObject;
import org.akidra.rentalapa.entities.DatePeriod; // Добавлен импорт
import org.akidra.rentalapa.repositories.RentObjectRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RentObjectService {
    private final RentObjectRepository rentObjectRepository;

    public RentObjectService(RentObjectRepository rentObjectRepository) {
        this.rentObjectRepository = rentObjectRepository;
    }

    public List<RentObject> getAllRentObjects() {
        return rentObjectRepository.findAll();
    }

    public RentObject getRentObjectById(UUID id) {
        return rentObjectRepository.findById(id).orElse(null);
    }

    public RentObject saveRentObject(RentObject rentObject) {
        return rentObjectRepository.save(rentObject);
    }

    public void deleteRentObject(UUID id) {
        rentObjectRepository.deleteById(id);
    }

    public RentObject reservePeriod(UUID id, LocalDate startDate, LocalDate endDate) {
        RentObject rentObject = getRentObjectById(id);
        if (rentObject != null) {
            rentObject.getReservedPeriods().add(new DatePeriod(startDate, endDate));
            return rentObjectRepository.save(rentObject);
        }
        return null;
    }

    public List<RentObject> searchRentObjects(
            String city,
            String country,
            LocalDate startDate,
            LocalDate endDate,
            Double minPrice,
            Double maxPrice,
            Double minRating) {

        List<RentObject> objects = rentObjectRepository.findByFilters(
                city, country, minPrice, maxPrice, minRating
        );

        if (startDate != null && endDate != null) {
            objects.removeIf(obj -> !isAvailableForPeriod(obj, startDate, endDate));
        }

        return objects;
    }

    private boolean isAvailableForPeriod(RentObject obj, LocalDate start, LocalDate end) {
        return obj.getAvailablePeriods().stream()
                .anyMatch(ap -> !ap.getStartDate().isAfter(start) &&
                        !ap.getEndDate().isBefore(end))
                &&
                obj.getReservedPeriods().stream()
                        .noneMatch(rp -> !rp.getEndDate().isBefore(start) &&
                                !rp.getStartDate().isAfter(end));
    }
}