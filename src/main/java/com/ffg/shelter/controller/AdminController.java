package com.ffg.shelter.controller;

import com.ffg.shelter.converter.AdminTransformer;
import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.view.CampCaseMangerView;
import com.ffg.shelter.view.UserView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/api/user")
public class AdminController extends ServiceBasedRestController<CsbUser, Long, AdminService> {
    @Inject
    @Named("adminService")
    @Override
    public void setService(AdminService service) {
        this.service = service;
    }

    private AdminTransformer userTransformer;

    @Inject
    @Named("userTransformer")
    public void setUserTransformer(AdminTransformer userTransformer) {
        this.userTransformer = userTransformer;
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public List<UserView> findAllUsers() throws IOException {
        List<UserView> listOfUsers = new ArrayList<UserView>();
        for (CsbUser user : this.service.findAll()) {
            listOfUsers.add(userTransformer.transformFromEntityToUserView(user));
        }
        return listOfUsers;
    }

    @RequestMapping(value = "managerList")
    @ResponseBody
    public List<CampCaseMangerView> findAllCaseManager() throws IOException {
        return userTransformer.transformFromEntityToCampCaseMangerView(this.service.findAll());

    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestParam(value = "userId", required = false) Long userId) throws IOException {
        this.service.delete(userId);
    }


    @RequestMapping(value = "details")
    @ResponseBody
    public UserView getUserDetails(@RequestParam(value = "userId", required = false) Long userId) throws IOException {
        return userTransformer.transformFromEntityToUserView(this.service.findById(userId));
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public UserView createUser(@RequestBody UserView userView) throws Exception {
        return userTransformer.transformFromEntityToUserView(this.service.create(userTransformer.transToEntityFromUserView(userView)));
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public UserView updateUser(@RequestBody UserView userView) throws Exception {
        return userTransformer.transformFromEntityToUserView(this.service.update(userTransformer.transToEntityFromUserView(userView)));
    }

    @RequestMapping(value = "listByPage")
    @ResponseBody
    public Page<UserView> findAllUsersPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                @RequestParam(value = "properties", required = false) String properties) throws Exception {

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if (direction.isEmpty()) {
            return userTransformer.buildUserViewPage(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            return userTransformer.buildUserViewPage(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

    }


}

