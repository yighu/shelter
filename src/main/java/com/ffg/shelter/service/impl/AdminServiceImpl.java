package com.ffg.shelter.service.impl;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.repository.AdminRepository;
import com.ffg.shelter.service.AdminService;
import com.google.appengine.api.users.User;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Transactional
@Named("adminService")
public class AdminServiceImpl extends CrudServiceImpl<CsbUser, Long, AdminRepository> implements AdminService {
    @Override
    @Inject
    public void setRepository(AdminRepository userRepository) {
        super.setRepository(userRepository);
    }

    public CsbUser getUser() throws AuthorizationException {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();
        User googleUser = null;

        if (authentication != null) {
            googleUser = (User) authentication.getPrincipal();

            if (googleUser != null) {
                return getUserIdByEmail(googleUser.getEmail());
            }
        }
        throw new AuthorizationException(" Not a Valid User ");
    }


    public CsbUser getUserIdByEmail(String userEmail) throws AuthorizationException {
        List<CsbUser> userList = this.repository.findAll();
        if (userEmail != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getEmail().equalsIgnoreCase(userEmail)) {
                    return csbUser;
                }
            }
        }
        throw new AuthorizationException(" Not a Valid User ");
    }

    public boolean isValidUser() throws AuthorizationException {
        if (getUser() != null) {
            return true;
        }
        ;
        return false;
    }

}
