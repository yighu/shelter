package com.ffg.shelter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity
public class CaseNotes implements Serializable {

    public Long id;
    public Long clientId;
    public String caseMng;
    public Date createdDate;
    public HousingStage housingStage;
    public String note;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCaseMng() {
        return caseMng;
    }

    public void setCaseMng(String caseMng) {
        this.caseMng = caseMng;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public HousingStage getHousingStage() {
        return housingStage;
    }

    public void setHousingStage(HousingStage housingStage) {
        this.housingStage = housingStage;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
