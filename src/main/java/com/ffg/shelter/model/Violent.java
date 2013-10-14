package com.ffg.shelter.model;


public enum Violent {
    AGGRESSIVE("Aggressive"),
    NOT_AGGRESSIVE("Not Aggressive");

    private String name;

    private Violent(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
