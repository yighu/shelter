package com.ffg.shelter.view;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 8/5/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserWorkflowView {
    private String workFlowName;
    private List<UserFeatureView> userFeatureViewList;


    public List<UserFeatureView> getUserFeatureViewList() {
        return userFeatureViewList;
    }

    public void setUserFeatureViewList(List<UserFeatureView> userFeatureViewList) {
        this.userFeatureViewList = userFeatureViewList;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }


}
