package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/9/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerCampView {
    private String campName;
    private String lastVisitedDate;
    private String lastVisitedCaseManager;
    private String nextVisitDate;
    private String nextVisingCaseManager;
    private boolean violent;

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getLastVisitedDate() {
        return lastVisitedDate;
    }

    public void setLastVisitedDate(String lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }

    public String getLastVisitedCaseManager() {
        return lastVisitedCaseManager;
    }

    public void setLastVisitedCaseManager(String lastVisitedCaseManager) {
        this.lastVisitedCaseManager = lastVisitedCaseManager;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public String getNextVisingCaseManager() {
        return nextVisingCaseManager;
    }

    public void setNextVisingCaseManager(String nextVisingCaseManager) {
        this.nextVisingCaseManager = nextVisingCaseManager;
    }

    public boolean isViolent() {
        return violent;
    }

    public void setViolent(boolean violent) {
        this.violent = violent;
    }
}
