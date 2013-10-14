package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/1/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsFeedView {

    private String id;
    private String eventId;
    private String comments;
    private String campName;
    private String createdBy;
    private String createdByDate;
    private String messageType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCreatedByDate() {
        return createdByDate;
    }

    public void setCreatedByDate(String createdByDate) {
        this.createdByDate = createdByDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

}
