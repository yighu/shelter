package com.ffg.shelter.converter;

import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.view.CampCaseMangerView;
import com.ffg.shelter.view.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface AdminTransformer {
    UserView transformFromEntityToUserView(CsbUser user);

    CsbUser transToEntityFromUserView(UserView userView) throws Exception;

    Page<UserView> buildUserViewPage(PageRequest pageRequest) throws Exception;

    List<CampCaseMangerView> transformFromEntityToCampCaseMangerView(List<CsbUser> csbUsers);
}
