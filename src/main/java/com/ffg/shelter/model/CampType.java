package com.ffg.shelter.model;


public enum CampType {
    BRIDGE("Bridge"),
    WOODS("Woods"),
    ABANDONED_BUILDING("Abandoned Building"),
    PARK("Park"),
    DOWNTOWN("Downtown"),
    OTHER("Other");

    private String name;

    private CampType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
