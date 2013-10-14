package com.ffg.shelter.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/7/13
 * Time: 2:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerEventDayView {
    private String day;
    private List<SchedulerEventView> events = new ArrayList<SchedulerEventView>();

    public List<SchedulerEventView> getEvents() {
        return events;
    }

    public void setEvents(List<SchedulerEventView> eventListByDay) {
        this.events = eventListByDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
