package com.ffg.shelter.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/30/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FeatureView {
    private Long id;
    private String featureName;
    private String workFlowName;
    private String privilegeType;

    private String role;
    private List<String> action;
    private List<String> privilegeTypes;

    public FeatureView() {
        action = new ArrayList<String>();
        privilegeTypes = new ArrayList<String>();
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

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

    public List<String> getAction() {
        return action;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getPrivilegeTypes() {
        return privilegeTypes;
    }

    public void setPrivilegeTypes(List<String> privilegeTypes) {
        this.privilegeTypes = privilegeTypes;
    }
}
