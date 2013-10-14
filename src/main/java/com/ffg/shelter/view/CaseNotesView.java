package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: Amanda
 * Date: 8/11/13
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class CaseNotesView {

    public String id;
    public String clientName;
    public String clientId;
    public String caseMngName;
    public String createdDate;
    public String housingStage;
    public String note;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCaseMngName() {
        return caseMngName;
    }

    public void setCaseMngName(String caseMngName) {
        this.caseMngName = caseMngName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


    public String getHousingStage() {
        return housingStage;
    }

    public void setHousingStage(String housingStage) {
        this.housingStage = housingStage;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

