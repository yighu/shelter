package com.ffg.shelter.model;

/**
 * Created with IntelliJ IDEA.
 * User: V005480
 * Date: 6/13/13
 * Time: 6:34 PM
 */
public enum Need {
    Transportation("Transportation"),
    HealthCare("Health care"),
    Food("Food"),
    Clothing("Clothing"),
    MedicalSupplies("Medical Supplies"),
    PersonalHygiene("Personal Hygiene"),
    LinkageToServices("Linkage to Services");

    private String name;

    private Need(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
