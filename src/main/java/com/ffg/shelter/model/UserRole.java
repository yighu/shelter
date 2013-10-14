package com.ffg.shelter.model;


public enum UserRole {
    CaseManager("Case Manager"),
    Admin("Admin"),
    Other("Other");
    private String value;

    private UserRole(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
