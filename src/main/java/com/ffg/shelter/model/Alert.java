package com.ffg.shelter.model;


public enum Alert {
    Partner("Bring a partner"),
    Escort("CPD Escort"),
    Park("Parks & Rec"),
    ODOT("ODOT"),
    RR("RR"),
    Metro("Metro");

    private String name;

    private Alert(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
