package com.ffg.shelter.service;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.Feature;
import com.ffg.shelter.model.PrivilegeType;
import com.ffg.shelter.view.UserWorkflowView;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface FeatureService extends CrudService<Feature, Long> {
    boolean isUserAuthorizationForFeature(String featureName) throws AuthorizationException, BusinessException;

    //this is for main index.html page
    List<UserWorkflowView> privilegeTypeByUserForWorkFlow();

    // this is as part of every http post every view will be send back with privilege list
    List<PrivilegeType> privilegeTypeByUserForFeature(String featureName) throws AuthorizationException;
}
