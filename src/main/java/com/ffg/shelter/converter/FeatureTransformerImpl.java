package com.ffg.shelter.converter;

import com.ffg.shelter.model.Feature;
import com.ffg.shelter.model.PrivilegeType;
import com.ffg.shelter.model.Role;
import com.ffg.shelter.service.PrivilegeTypeService;
import com.ffg.shelter.service.RoleService;
import com.ffg.shelter.view.FeatureView;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named("featureTransformer")
public class FeatureTransformerImpl implements FeatureTransformer {

    private RoleService roleService;

    @Inject
    @Named("roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private PrivilegeTypeService privilegeTypeService;

    @Inject
    @Named("privilegeTypeService")
    public void setPrivilegeTypeService(PrivilegeTypeService privilegeTypeService) {
        this.privilegeTypeService = privilegeTypeService;
    }

    public FeatureView transformFromEntityToFeatureView(Feature feature) {
        FeatureView featureView = new FeatureView();
        List<Role> roles = roleService.findAll();
        for (Role role : roles) {
            featureView.getPrivilegeTypes().add(role.getRoleType().toString());
        }
        List<PrivilegeType> allPrivileges = privilegeTypeService.findAll();
        for (PrivilegeType privilegeType : allPrivileges) {
            featureView.getPrivilegeTypes().add(privilegeType.getPrivilegeType());
        }

        featureView.setFeatureName(feature.getFeatureName());
        featureView.setId(feature.getId());
        featureView.setWorkFlowName(feature.getWorkFlowName());
        return featureView;
    }

    public Feature transformFromViewToEntity(FeatureView featureView) {

        Feature feature = new Feature();
        if (featureView.getId() != null) {
            feature.setId(featureView.getId());
        }

        feature.setFeatureName(featureView.getFeatureName());
        feature.setWorkFlowName(featureView.getWorkFlowName());
        List<PrivilegeType> privilegeTypes = privilegeTypeService.findByPrivilegeType(featureView.getPrivilegeType());
        if (privilegeTypes != null) {

            feature.setPrivilegeType(privilegeTypes);
        }

        List<Role> roleList = roleService.findByRoleName(featureView.getRole());

        if (roleList != null) {
            feature.setRoles(roleList);
        }
        return feature;
    }
}
