package org.akidra.rentalapa.entities;

public enum RentObjectType {
    ROOM("Комната"),
    APARTMENT("Квартира"),
    HOUSE("Дом"),
    VILLA("Вилла"),
    COTTAGE("Дача"),
    COMMERCIAL("Коммерческая недвижимость"),
    UNIQUE("Уникальное жилье");

    private final String displayName;

    RentObjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}