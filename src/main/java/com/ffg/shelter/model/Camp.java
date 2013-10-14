package com.ffg.shelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Camp implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String streetAddress;
    private CampBadge badge;
    @JsonIgnoreProperties
    protected Set<CampStats> campStats = new HashSet<CampStats>();
    @JsonIgnoreProperties
    protected Set<CsbUser> users = new HashSet<CsbUser>();
    private Calendar setUpDate;
    private Calendar scrubDate;
    private CampStatus status;
    private String latitude;
    private String longitude;
    private CampType type;
    @JsonIgnoreProperties
    protected Set<Client> clients;
    private Alert alert;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "camp_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<CsbUser> getUsers() {
        return users;
    }

    public void setUsers(Set<CsbUser> users) {
        this.users = users;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Enumerated(EnumType.STRING)
    public CampBadge getBadge() {
        return badge;
    }

    public void setBadge(CampBadge badge) {
        this.badge = badge;
    }


    public Calendar getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(Calendar setUpDate) {
        this.setUpDate = setUpDate;
    }

    public Calendar getScrubDate() {
        return scrubDate;
    }

    public void setScrubDate(Calendar scrubDate) {
        this.scrubDate = scrubDate;
    }

    public CampStatus getStatus() {
        return status;
    }

    public void setStatus(CampStatus status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Enumerated(EnumType.STRING)
    public CampType getType() {
        return type;
    }

    public void setType(CampType type) {
        this.type = type;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camp_id")
    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id", updatable = false, insertable = false, nullable = false)
    public Set<Client> getClients() {
        return clients;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "camp_id"),
            inverseJoinColumns = @JoinColumn(name = "campStats_id")
    )
    public Set<CampStats> getCampStats() {
        return campStats;
    }

    public void setCampStats(Set<CampStats> campStats) {
        this.campStats = campStats;
    }
}
