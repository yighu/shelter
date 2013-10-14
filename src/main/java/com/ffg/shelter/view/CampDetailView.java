package com.ffg.shelter.view;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/1/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampDetailView {
    private String id;
    @NotEmpty(message = "Camp Name Required")
    @Size(min = 2, max = 1000, message = "Camp Name Length  Error")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 1000, message = "Camp Description Length  Error")
    private String description;
    private String longitude;
    private String latitude;
    private String badge;
    @Pattern(regexp = "^((0)|(0[1-9])|(0[1-9]\\d{1,2})|([1-9])|([1-9]\\d{1,3}))$", message = "invalid campMaleCount count")
    private String campMaleCount;
    @Pattern(regexp = "^((0)|(0[1-9])|(0[1-9]\\d{1,2})|([1-9])|([1-9]\\d{1,3}))$", message = "invalid  campFemaleCount count")
    private String campFemaleCount;
    @Pattern(regexp = "^((0)|(0[1-9])|(0[1-9]\\d{1,2})|([1-9])|([1-9]\\d{1,3}))$", message = "invalid  campChildCount count")
    private String campChildCount;
    @Pattern(regexp = "^((0)|(0[1-9])|(0[1-9]\\d{1,2})|([1-9])|([1-9]\\d{1,3}))$", message = "invalid campPetCount count")
    private String campPetCount;
    @Pattern(regexp = "^((0)|(0[1-9])|(0[1-9]\\d{1,2})|([1-9])|([1-9]\\d{1,3}))$", message = "invalid  campStructureCount count")
    private String campStructureCount;
    private String campType;
    @Pattern(regexp = "^(0[1-9]|1[012])\\/([0-2][1-9]|[12][0]|3[0-2])\\/((19|20)\\d\\d)$", message = "invalid campScrubDate date format")
    private String campScrubDate;
    private String campScrubDay;
    private String campScrubMonth;
    private String campScrubYear;
    @Pattern(regexp = "^(0[1-9]|1[012])\\/([0-2][1-9]|[12][0]|3[0-2])\\/((19|20)\\d\\d)$", message = "invalid campScrubYear date format")
    private String lastVisitedDate;
    private String setUpDate;
    private String setUpDay;
    private String setUpMonth;
    private String setUpYear;
    private String status;
    @Size(min = 2, max = 1000, message = "Camp Location Length  Error")
    private String location;
    private String clientCount;
    private List<CampCaseMangerView> listOfCaseMgr;
    private String leadCaseMgr;
    private String caseMgrOne;
    private String caseMgrTwo;
    private String alert;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastVisitedDate() {
        return lastVisitedDate;
    }


    public void setLastVisitedDate(String lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getLeadCaseMgr() {
        return leadCaseMgr;
    }

    public void setLeadCaseMgr(String leadCaseMgr) {
        this.leadCaseMgr = leadCaseMgr;
    }

    public String getCaseMgrOne() {
        return caseMgrOne;
    }

    public void setCaseMgrOne(String caseMgrOne) {
        this.caseMgrOne = caseMgrOne;
    }

    public String getCaseMgrTwo() {
        return caseMgrTwo;
    }

    public void setCaseMgrTwo(String casemgrTwo) {
        this.caseMgrTwo = casemgrTwo;
    }

    public List<CampCaseMangerView> getListOfCaseMgr() {
        return listOfCaseMgr;
    }

    public void setListOfCaseMgr(List<CampCaseMangerView> listOfCaseMgr) {
        this.listOfCaseMgr = listOfCaseMgr;
    }

    public String getCampScrubDay() {
        return campScrubDay;
    }

    public void setCampScrubDay(String campScrubDay) {
        this.campScrubDay = campScrubDay;
    }

    public String getCampScrubMonth() {
        return campScrubMonth;
    }

    public void setCampScrubMonth(String campScrubMonth) {
        this.campScrubMonth = campScrubMonth;
    }

    public String getCampScrubYear() {
        return campScrubYear;
    }

    public void setCampScrubYear(String campScrubYear) {
        this.campScrubYear = campScrubYear;
    }

    public String getSetUpDay() {
        return setUpDay;
    }

    public void setSetUpDay(String setUpDay) {
        this.setUpDay = setUpDay;
    }

    public String getSetUpMonth() {
        return setUpMonth;
    }

    public void setSetUpMonth(String setUpMonth) {
        this.setUpMonth = setUpMonth;
    }

    public String getSetUpYear() {
        return setUpYear;
    }

    public void setSetUpYear(String setUpYear) {
        this.setUpYear = setUpYear;
    }

    public String getClientCount() {
        return clientCount;
    }

    public void setClientCount(String clientCount) {
        this.clientCount = clientCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getCampMaleCount() {
        return campMaleCount;
    }

    public void setCampMaleCount(String campMaleCount) {
        this.campMaleCount = campMaleCount;
    }

    public String getCampFemaleCount() {
        return campFemaleCount;
    }

    public void setCampFemaleCount(String campFemaleCount) {
        this.campFemaleCount = campFemaleCount;
    }

    public String getCampChildCount() {
        return campChildCount;
    }

    public void setCampChildCount(String campChildCount) {
        this.campChildCount = campChildCount;
    }

    public String getCampPetCount() {
        return campPetCount;
    }

    public void setCampPetCount(String campPetCount) {
        this.campPetCount = campPetCount;
    }

    public String getCampStructureCount() {
        return campStructureCount;
    }

    public void setCampStructureCount(String campStructureCount) {
        this.campStructureCount = campStructureCount;
    }

    public String getCampType() {
        return campType;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getCampScrubDate() {
        return campScrubDate;
    }

    public void setCampScrubDate(String campScrubDate) {
        this.campScrubDate = campScrubDate;
    }

    public String getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
