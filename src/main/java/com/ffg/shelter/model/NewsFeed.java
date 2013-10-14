package com.ffg.shelter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class NewsFeed implements Serializable {

    public Long id;
    public Long eventId;
    public String comments;
    public Long campId;
    public String createdBy;
    public Date createdDate;
    public Date expirationDate;
    public NewsType messageType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Enumerated(EnumType.STRING)
    public NewsType getMessageType() {
        return messageType;
    }


    public void setMessageType(NewsType messageType) {
        this.messageType = messageType;
    }
}
