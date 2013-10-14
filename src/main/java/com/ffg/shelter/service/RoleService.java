package com.ffg.shelter.service;

import com.ffg.shelter.model.Role;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface RoleService extends CrudService<Role, Long> {
    List<Role> findByRoleName(String roleType);
}
