package com.ffg.shelter.model;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Feature implements Serializable {
    private Long id;
    private String featureName;
    @Column(name = "workFlow_Name")
    @Index(name = "workFlowNameIndex")
    private String workFlowName;
    private List<Role> roles;
    private List<PrivilegeType> privilegeType;

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "privilegeType_id")
    )
    public List<PrivilegeType> getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(List<PrivilegeType> privilegeType) {
        this.privilegeType = privilegeType;
    }


    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }


}
