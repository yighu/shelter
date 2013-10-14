package com.ffg.shelter.model;


public enum MentalStatus {
    GOOD("Good"),
    COPING("Coping"),
    HOSPITALIZED("Hospitalized"),
    NOT_COPING("Not Coping");

    private String name;

    private MentalStatus(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
