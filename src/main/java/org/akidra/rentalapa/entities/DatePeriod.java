package org.akidra.rentalapa.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Embeddable
@Getter @Setter
public class DatePeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public DatePeriod() {}

    public DatePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}