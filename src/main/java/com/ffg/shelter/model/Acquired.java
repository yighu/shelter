package com.ffg.shelter.model;


public enum Acquired {

    Acquired("Acquired"),
    NotAcquired("Not Acquired");

    private String name;

    private Acquired(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
