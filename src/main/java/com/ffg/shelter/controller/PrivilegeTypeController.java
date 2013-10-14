package com.ffg.shelter.controller;

import com.ffg.shelter.model.PrivilegeType;
import com.ffg.shelter.service.PrivilegeTypeService;
import com.ffg.shelter.view.PrivilegeTypeView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/api/privilege")
public class PrivilegeTypeController extends ServiceBasedRestController<PrivilegeType, Long, PrivilegeTypeService> {


    @Inject
    @Named("privilegeTypeService")
    @Override
    public void setService(PrivilegeTypeService service) {
        this.service = service;
    }


    @RequestMapping(value = "all")
    @ResponseBody
    public List<PrivilegeType> findAllUsers() throws IOException {
        return this.service.findAll();
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deletePrivilegeType(@RequestParam(value = "privilegeId", required = false) Long privilegeId) throws IOException {
        this.service.delete(privilegeId);
    }


    @RequestMapping(value = "details")
    @ResponseBody
    public PrivilegeTypeView getPrivilegeTypeDetails(@RequestParam(value = "privilegeId", required = false) Long privilegeId) throws IOException {
        return convert(this.service.findById(privilegeId));
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public PrivilegeTypeView createPrivilegeType(@RequestBody PrivilegeTypeView privilegeTypeView) throws Exception {
        PrivilegeType privilegeType = new PrivilegeType();
        privilegeType.setPrivilegeType(privilegeTypeView.getAction());
        return convert(this.service.create(privilegeType));
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public PrivilegeTypeView updatePrivilegeType(@RequestBody PrivilegeTypeView privilegeTypeView) throws Exception {
        PrivilegeType privilegeType = new PrivilegeType();
        privilegeType.setPrivilegeType(privilegeTypeView.getAction());
        privilegeType.setId(privilegeTypeView.getId());
        return convert(this.service.update(privilegeType));
    }

    private PrivilegeTypeView convert(PrivilegeType privilegeType) {
        PrivilegeTypeView privilegeTypeView = new PrivilegeTypeView();
        privilegeTypeView.setAction(privilegeType.getPrivilegeType());
        privilegeTypeView.setId(privilegeType.getId());
        return privilegeTypeView;
    }
}
