package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/7/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampScheduleCheckInView {
    private String campName;
    private Long campId;
    private boolean checkIn;
    private String scheduleDate;
    private String scheduleDay;
    private String scheduleMonth;
    private String scheduleYear;

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleMonth() {
        return scheduleMonth;
    }

    public void setScheduleMonth(String scheduleMonth) {
        this.scheduleMonth = scheduleMonth;
    }

    public String getScheduleYear() {
        return scheduleYear;
    }

    public void setScheduleYear(String scheduleYear) {
        this.scheduleYear = scheduleYear;
    }

}
