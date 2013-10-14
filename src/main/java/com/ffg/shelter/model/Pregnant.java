package com.ffg.shelter.model;


public enum Pregnant {

    NA("N/A"),
    No("No"),
    FirstTrimester("1st Trimester"),
    SecondTrimester("2nd Trimester"),
    ThirdTrimester("3rd Trimester");

    private String value;

    private Pregnant(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
