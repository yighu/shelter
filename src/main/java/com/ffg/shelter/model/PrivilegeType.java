package com.ffg.shelter.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class PrivilegeType {
    private Long id;
    private String privilegeType;
    private List<Feature> listOfFeature;


    @ManyToMany
    @JoinColumn(name = "feature_id", updatable = false, insertable = false, nullable = false)
    public List<Feature> getListOfFeature() {
        return listOfFeature;
    }

    public void setListOfFeature(List<Feature> listOfFeature) {
        this.listOfFeature = listOfFeature;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegeType_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }
}
