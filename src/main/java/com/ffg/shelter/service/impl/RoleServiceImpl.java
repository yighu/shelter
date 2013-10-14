package com.ffg.shelter.service.impl;

import com.ffg.shelter.model.Role;
import com.ffg.shelter.repository.RoleRepository;
import com.ffg.shelter.service.RoleService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Transactional
@Named("roleService")
public class RoleServiceImpl extends CrudServiceImpl<Role, Long, RoleRepository> implements RoleService {

    @Override
    @Inject
    public void setRepository(RoleRepository repository) {
        super.setRepository(repository);
    }

    public List<Role> findByRoleName(String roleType) {
        List<Role> roleList = this.repository.findByRoleName(roleType);
        return roleList
                ;
    }
}
