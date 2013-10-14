package com.ffg.shelter.converter;

import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.model.UserRole;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.view.CampCaseMangerView;
import com.ffg.shelter.view.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Named("userTransformer")
public class AdminTransformerImpl implements AdminTransformer {

    private AdminService service;

    @Inject
    @Named("adminService")
    public void setService(AdminService service) {
        this.service = service;
    }

    public UserView transformFromEntityToUserView(CsbUser user) {
        UserView userView = new UserView();
        userView.setUserEmail(user.getEmail());
        userView.setUserFirstName(user.getFirstName());
        userView.setUserLastName(user.getLastName());
        userView.setUserId(user.getId());
        userView.setUserRole(user.getRole().toString());
        Calendar calendar = user.getExpiryDate();
        if (calendar != null) {
            userView.setUserExpiryDate(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
        }
        userView.setUserExpiryMonth(calendar.get(Calendar.MONTH) + "");
        userView.setUserExpiryDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
        userView.setUserExpiryYear(calendar.get(Calendar.YEAR) + "");
        return userView;
    }

    public CsbUser transToEntityFromUserView(UserView userView) throws Exception {
        CsbUser user = new CsbUser();
        if (userView.getUserId() != null) {
            user.setId(userView.getUserId());
        }
        user.setEmail(userView.getUserEmail());
        user.setFirstName(userView.getUserFirstName());
        user.setLastName(userView.getUserLastName());
        user.setRole(UserConverter.convertUserRole(userView.getUserRole()));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar eDate = Calendar.getInstance();
        eDate.setTime(formatter.parse(userView.getUserExpiryDate()));
        user.setExpiryDate(eDate);
        return user;
    }

    public Page<UserView> buildUserViewPage(PageRequest pageRequest) throws Exception {
        List<UserView> listOfUsers = new ArrayList<UserView>();
        Page<CsbUser> pageOfCsbUser = null;
        PageRequest pageRequestResponse = null;
        pageOfCsbUser = this.service.findAll(pageRequest);
        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfCsbUser.getNumber(), pageOfCsbUser.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfCsbUser.getNumber(), pageOfCsbUser.getSize(), pageOfCsbUser.getSort());
        }

        if (pageOfCsbUser != null) {
            for (CsbUser user : pageOfCsbUser.getContent()) {
                listOfUsers.add(transformFromEntityToUserView(user));
            }
        }

        PageImpl page = new PageImpl(listOfUsers, pageRequestResponse, pageOfCsbUser.getTotalElements());

        return page;
    }

    public List<CampCaseMangerView> transformFromEntityToCampCaseMangerView(List<CsbUser> csbUsers) {
        List<CampCaseMangerView> caseMangerViewList = new ArrayList<CampCaseMangerView>();
        for (CsbUser csbUser : csbUsers) {
            CampCaseMangerView caseMangerView = new CampCaseMangerView();
            if ((csbUser.getRole() == UserRole.Admin) || (csbUser.getRole() == UserRole.CaseManager)) {
                caseMangerView.setName(csbUser.getFirstName() + " " + csbUser.getLastName());
                caseMangerViewList.add(caseMangerView);
            }
        }
        return caseMangerViewList;
    }

}
