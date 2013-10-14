package com.ffg.shelter.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/7/13
 * Time: 2:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerEventMonthView {
    private String month;
    private List<SchedulerEventDayView> eventList = new ArrayList<SchedulerEventDayView>();

    public List<SchedulerEventDayView> getEventList() {
        return eventList;
    }

    public void setEventList(List<SchedulerEventDayView> eventListByDayOfTheMonth) {
        this.eventList = eventListByDayOfTheMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
