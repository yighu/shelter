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
import java.util.logging.Logger;


@Controller
public class LoginController {
    private AdminService adminService;
private static final Logger log=Logger.getLogger(LoginController.class.getName());

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam String redirect, HttpServletResponse response) throws AuthorizationException, IOException {
            UserService userService = UserServiceFactory.getUserService();
	    log.info("Is user loggedin:"+userService.isUserLoggedIn());
            User googleUser = userService.getCurrentUser();
            log.info("current user googleUser=" + googleUser);
        	if (redirect == null) {
		log.info("no redirect set so make it to root page");
		redirect="#/";
		}

            if (googleUser != null) {
                try {
                    // have to check is the user is valid in the user store
           CsbUser csbUser = adminService.getUserIdByEmail(googleUser.getEmail());
                log.info("current user CSB USER=" + csbUser);
                    return "redirect:"+redirect;
                } catch (AuthorizationException e) {
                    log.info("redirect to login for unauthorised user exception " +e.toString() );
                return "redirect:" + userService.createLoginURL(redirect);
                }

            } else {
                log.info("redirect to login for invalid null user googleUser" );
                return "redirect:" + userService.createLoginURL(redirect);
            }
    }


    @RequestMapping(value = "/logout")
    public String logout(@RequestParam String redirect) {
        if (redirect == null) {
		redirect="#/";
		}
		log.info("log out user");
            UserService userService = UserServiceFactory.getUserService();
            return "redirect:" + userService.createLogoutURL(redirect);
        
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
            log.info("in admin csbUser User" + csbUser);
                    role.setUserRole(csbUser.getRole().toString());
                    role.setAdmin(csbUser.getRole() == UserRole.Admin ? true : false);

                }else{
			log.info("in admin, user null");
		}
            }
        } catch (AuthorizationException e) {
            log.info("Not an Admin User" + e);
        } catch (NullPointerException e) {
            log.info("in login controller NullPointerException=" + e);
        }
        return role;
    }
}
