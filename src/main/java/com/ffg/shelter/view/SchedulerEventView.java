package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/1/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerEventView {


    private String campEvent;
    private String eventType;
    private Long campId;
    private String eventDay;

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public String getCampEvent() {
        return campEvent;
    }

    public void setCampEvent(String campEvent) {
        this.campEvent = campEvent;
    }
}
