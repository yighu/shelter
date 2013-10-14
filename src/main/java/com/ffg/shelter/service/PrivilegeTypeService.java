package com.ffg.shelter.service;

import com.ffg.shelter.model.PrivilegeType;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface PrivilegeTypeService extends CrudService<PrivilegeType, Long> {
    List<PrivilegeType> findByPrivilegeType(String privilegeType);
}
