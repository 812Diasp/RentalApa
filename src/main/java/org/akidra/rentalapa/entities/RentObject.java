package org.akidra.rentalapa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
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

    @ElementCollection
    private List<LocalDate> reserved;

    @ElementCollection
    private List<LocalDate> availableRentSpan;

    @ManyToOne
    @JoinColumn(name = "rentman_id")
    private RentMan rentMan;
}