package com.ffg.shelter.controller;

import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.model.Role;
import com.ffg.shelter.model.UserRole;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.RoleService;
import com.ffg.shelter.view.RoleView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/api/role")
public class RoleController extends ServiceBasedRestController<Role, Long, RoleService> {

    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Inject
    @Named("roleService")
    @Override
    public void setService(RoleService service) {
        this.service = service;
    }

    @RequestMapping(value = "all")
    @ResponseBody
    public List<Role> findAllUsers() throws IOException {
        return this.service.findAll();
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteRole(@RequestParam(value = "roleId", required = false) Long roleId) throws IOException {
        this.service.delete(roleId);
    }


    @RequestMapping(value = "details")
    @ResponseBody
    public RoleView getRoleDetails(@RequestParam(value = "roleId", required = false) Long roleId) throws IOException {
        return convertRole(this.service.findById(roleId));
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public RoleView createRole(@RequestBody RoleView roleView) throws Exception {
        Role role = new Role();
        role.setRoleType(roleView.getUserRole());
        return convertRole(this.service.update(role));
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public RoleView updateRole(@RequestBody RoleView roleView) throws Exception {
        Role role = new Role();
        role.setId(roleView.getRoleId());
        role.setRoleType(roleView.getUserRole());
        return convertRole(this.service.update(role));
    }


    private RoleView convertRole(Role role) {
        RoleView roleView = new RoleView();
        roleView.setRoleId(role.getId());
        roleView.setUserRole(role.getRoleType());
        return roleView;
    }

    @RequestMapping(value = "userType")
    @ResponseBody
    public RoleView isAdmin() throws Exception {
        RoleView role = new RoleView();
        CsbUser csbUser = adminService.getUser();
        role.setUserRole(csbUser.getRole().toString());
        role.setAdmin(csbUser.getRole() == UserRole.Admin ? true : false);
        return role;
    }

}
