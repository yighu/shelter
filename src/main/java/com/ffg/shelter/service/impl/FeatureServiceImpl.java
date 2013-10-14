package com.ffg.shelter.service.impl;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.*;
import com.ffg.shelter.repository.FeatureRepository;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.FeatureService;
import com.ffg.shelter.view.PrivilegeTypeView;
import com.ffg.shelter.view.UserFeatureView;
import com.ffg.shelter.view.UserWorkflowView;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Named("featureService")
public class FeatureServiceImpl extends CrudServiceImpl<Feature, Long, FeatureRepository> implements FeatureService {

    @Override
    @Inject
    public void setRepository(FeatureRepository repository) {
        super.setRepository(repository);
    }

    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    public boolean isUserAuthorizationForFeature(String featureName) throws AuthorizationException, BusinessException {
        boolean roleValidForFeature = false;
        CsbUser csbUser = adminService.getUser();
        List<Feature> featureList = this.repository.findAllFeatureByFeatureName(featureName);
        return checkForAuthorization(roleValidForFeature, csbUser, featureList);

    }

    private boolean checkForAuthorization(boolean roleValidForFeature, CsbUser csbUser, List<Feature> featureList) throws AuthorizationException {
        for (Feature feature : featureList) {
            for (Role role : feature.getRoles()) {
                if (role.getRoleType().equalsIgnoreCase(csbUser.getRole().toString())) {
                    roleValidForFeature = true;
                }
            }
        }
        if (roleValidForFeature) {
            return roleValidForFeature;
        } else {
            throw new AuthorizationException("Not Authorized For  the Feature");
        }
    }


    public CsbUser getUserIdByEmail(String userEmail) {
        List<CsbUser> userList = adminService.findAll();
        if (userEmail != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getEmail().equalsIgnoreCase(userEmail)) {
                    return csbUser;
                }
            }
        }
        return null;
    }

    public List<UserWorkflowView> privilegeTypeByUserForWorkFlow() {
        List<UserWorkflowView> listWorkFlow = new ArrayList<UserWorkflowView>();
        for (WorkFlow workFlow : WorkFlow.values()) {
            UserWorkflowView userWorkflowView = new UserWorkflowView();
            userWorkflowView.setWorkFlowName(workFlow.name());
            List<Feature> listOfFeature = this.repository.findAllFeatureByWorkFlow(workFlow.name());
            List<UserFeatureView> listOfFeatures = new ArrayList<UserFeatureView>();
            userWorkflowView.setUserFeatureViewList(listOfFeatures);
            for (Feature feature : listOfFeature) {
                UserFeatureView userFeatureView = new UserFeatureView();
                List<PrivilegeTypeView> listPrivilege = new ArrayList<PrivilegeTypeView>();
                for (PrivilegeType privilegeType : feature.getPrivilegeType()) {
                    PrivilegeTypeView privilegeTypeView = new PrivilegeTypeView();
                    privilegeTypeView.setAction(privilegeType.getPrivilegeType());
                    listPrivilege.add(privilegeTypeView);
                }
                userFeatureView.setPrivilegeTypeViews(listPrivilege);
                userFeatureView.setFeature(feature.getFeatureName());
                listOfFeatures.add(userFeatureView);
            }
            listWorkFlow.add(userWorkflowView);
        }
        return listWorkFlow;
    }

    public List<PrivilegeType> privilegeTypeByUserForFeature(String featureName) throws AuthorizationException {
        List<Feature> featureList = this.repository.findAllFeatureByFeatureName(featureName);
        List<PrivilegeType> privilegeTypeList;
        for (Feature feature : featureList) {
            if (feature.getFeatureName().equalsIgnoreCase(featureName)) {
                return feature.getPrivilegeType();
            }
        }
        return null;
    }
}
