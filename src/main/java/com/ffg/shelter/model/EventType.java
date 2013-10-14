package com.ffg.shelter.model;


public enum EventType {
    ScheduleCampVisit("Schedule Camp Visit"),
    CampVisited("Visited"),
    CampScrubbed(" Camp Scrubbed "),
    CampScheduledForScrubbing("Camp Scheduled For Scrubbing "),
    AssignLeadCampMgr(" Assign Lead Camp Manager"),
    AssignCampMgrOne(" Assign Camp Manager One"),
    AssignCampMgrTwo(" Assign Camp Manager Two");

    private String name;

    private EventType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
