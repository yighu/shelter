package com.ffg.shelter.service;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.model.CsbUser;
import org.resthub.common.service.CrudService;


public interface AdminService extends CrudService<CsbUser, Long> {
    CsbUser getUser() throws AuthorizationException;

    boolean isValidUser() throws AuthorizationException;

    CsbUser getUserIdByEmail(String userEmail) throws AuthorizationException;

}
