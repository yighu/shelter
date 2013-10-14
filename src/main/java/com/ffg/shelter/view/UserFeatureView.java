package com.ffg.shelter.view;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 8/5/13
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserFeatureView {
    private String feature;
    private List<PrivilegeTypeView> privilegeTypeViews;

    public List<PrivilegeTypeView> getPrivilegeTypeViews() {
        return privilegeTypeViews;
    }

    public void setPrivilegeTypeViews(List<PrivilegeTypeView> privilegeTypeViews) {
        this.privilegeTypeViews = privilegeTypeViews;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }


}
