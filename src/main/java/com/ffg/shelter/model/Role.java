package com.ffg.shelter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Role implements Serializable {
    private Long id;
    private String roleType;
    private List<Feature> listOfFeature;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

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
    @Column(name = "role_id")
    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }


}
