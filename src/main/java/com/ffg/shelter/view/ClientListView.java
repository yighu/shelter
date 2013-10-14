package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/3/13
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientListView {
    private Long id;
    private String name;
    private String attitude;
    private String lastContact;
    private ClientCampView camp;
    private String age;
    private String status;
    private String numOfChildren;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getLastContact() {
        return lastContact;
    }

    public void setLastContact(String lastContact) {
        this.lastContact = lastContact;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(String numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public ClientCampView getCamp() {
        return camp;
    }

    public void setCamp(ClientCampView camp) {
        this.camp = camp;
    }

}
