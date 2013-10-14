package com.ffg.shelter.controller;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.model.UserRole;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.view.RoleView;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam String redirect, HttpServletResponse response) throws AuthorizationException, IOException {
        if (redirect != null) {

            UserService userService = UserServiceFactory.getUserService();
            User googleUser = userService.getCurrentUser();
            if (googleUser != null) {
                try {
                    // have to check is the user is valid in the user store
                    adminService.getUserIdByEmail(googleUser.getEmail());
                } catch (AuthorizationException e) {
                    System.out.println("invalid user a google user but not app user" + e);
                    return "redirect:#/";
                }

                return "redirect:" + userService.createLoginURL(redirect);
            } else {
                System.out.println("invalid user googleUser=" + googleUser);
                // Redirect to google login and then navigate to home, as the user is not logged in to google
                return "redirect:" + userService.createLoginURL("#/");
            }
        }
        throw new IllegalArgumentException("redirect not specified");
    }


    @RequestMapping(value = "/logout")
    public String logout(@RequestParam String redirect) {
        if (redirect != null) {
            UserService userService = UserServiceFactory.getUserService();
            return "redirect:" + userService.createLogoutURL(redirect);
        }
        throw new IllegalArgumentException("redirect not specified");
    }

    @RequestMapping(value = "/admin")
    @ResponseBody
    public RoleView isAdmin() {
        RoleView role = new RoleView();
        role.setAdmin(false);
        UserService userService = UserServiceFactory.getUserService();
        try {
            if (userService != null) {
                User googleUser = userService.getCurrentUser();
                if (googleUser != null) {

                    CsbUser csbUser = adminService.getUserIdByEmail(googleUser.getEmail());
                    role.setUserRole(csbUser.getRole().toString());
                    role.setAdmin(csbUser.getRole() == UserRole.Admin ? true : false);

                }
            }
        } catch (AuthorizationException e) {
            System.out.println("Not an Admin User" + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException=" + e);
        }
        return role;
    }
}
