package org.akidra.rentalapa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "rent_objects")
public class RentObject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RentObjectType rentObjectType;

    private Double price;
    private Double deposit;
    private Double area;
    private String floor;
    private Double countOfRooms;
    private String address;
    private Double commission;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "rent_object_images", joinColumns = @JoinColumn(name = "rent_object_id"))
    @Column(name = "image_url", length = 2048)
    private List<String> imageUrls;

    @ElementCollection
    @CollectionTable(name = "reserved_periods", joinColumns = @JoinColumn(name = "rent_object_id"))
    private List<DatePeriod> reservedPeriods;

    @ElementCollection
    @CollectionTable(name = "available_periods", joinColumns = @JoinColumn(name = "rent_object_id"))
    private List<DatePeriod> availablePeriods;

    @ManyToOne
    @JoinColumn(name = "rentman_id")
    private RentMan rentMan;

    // Методы для работы с периодами
    public List<DatePeriod> getReservedPeriods() {
        return reservedPeriods;
    }

    public List<DatePeriod> getAvailablePeriods() {
        return availablePeriods;
    }
}