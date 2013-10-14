package com.ffg.shelter.service.impl;

import com.ffg.shelter.model.PrivilegeType;
import com.ffg.shelter.repository.PrivilegeTypeRepository;
import com.ffg.shelter.service.PrivilegeTypeService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Transactional
@Named("privilegeTypeService")
public class PrivilegeTypeServiceImpl extends CrudServiceImpl<PrivilegeType, Long, PrivilegeTypeRepository> implements PrivilegeTypeService {

    @Override
    @Inject
    public void setRepository(PrivilegeTypeRepository repository) {
        super.setRepository(repository);
    }

    public List<PrivilegeType> findByPrivilegeType(String privilegeType) {
        List<PrivilegeType> privilegeTypeList = this.repository.findByPrivilegeType(privilegeType);
        return privilegeTypeList;
    }
}
