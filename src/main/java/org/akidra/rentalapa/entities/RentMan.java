package org.akidra.rentalapa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class RentMan extends User {
    private UUID rentmanId;
    private String phoneNumber;

    @OneToMany(mappedBy = "rentMan", cascade = CascadeType.ALL)
    private List<RentObject> rentObjects;

    @OneToMany(mappedBy = "rentMan", cascade = CascadeType.ALL)
    private List<Review> rentmanReviews;

    private Double rentorRating;
}