package com.ffg.shelter.model;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Index(name = "eventDateIndex")
    private Timestamp eventDate;
    private Timestamp eventExpiryDate;
    @Index(name = "eventOwnerIndex")
    private Long eventOwner;
    @Index(name = "eventCampIndex")
    private Long campId;

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public Long getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(Long eventOwner) {
        this.eventOwner = eventOwner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }


    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public Timestamp getEventExpiryDate() {
        return eventExpiryDate;
    }

    public void setEventExpiryDate(Timestamp eventExpiryDate) {
        this.eventExpiryDate = eventExpiryDate;
    }
}
