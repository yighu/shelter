package com.ffg.shelter.controller;

import com.ffg.shelter.converter.CampTransformer;
import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.ValidationErrors;
import com.ffg.shelter.model.Camp;
import com.ffg.shelter.model.CampStatus;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.FeatureService;
import com.ffg.shelter.view.CampDetailView;
import com.ffg.shelter.view.CampListView;
import com.ffg.shelter.view.CampSearchView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/api/camp")
public class CampController extends ServiceBasedRestController<Camp, Long, CampService> {

    private CampTransformer campTransformer;

    private FeatureService featureService;

    @Inject
    @Named("campService")
    @Override
    public void setService(CampService service) {
        this.service = service;
    }

    @Inject
    @Named("campTransformer")
    public void setClientTransformer(CampTransformer campTransformer) {
        this.campTransformer = campTransformer;
    }

    @Inject
    @Named("featureService")
    public void setFeatureService(FeatureService featureService) {
        this.featureService = featureService;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public List<Camp> getCampByName(@RequestParam(value = "campName", required = false) String campName) throws IOException {
        if (campName != null) {
            campName = '%' + campName.toUpperCase().replaceAll("\\s", "") + '%';
        }
        List<Camp> listOfCamps = this.service.findByCampName(campName);
        return listOfCamps;
    }

    @RequestMapping(value = "details")
    @ResponseBody
    public CampDetailView getCampDetails(@RequestParam(value = "campId", required = false) Long campId) throws Exception {
        return campTransformer.transformFromEntityToDetailView(this.service.findById(campId));
    }


    @RequestMapping(value = "add")
    @ResponseBody
    public CampDetailView createCamp(@Valid @RequestBody CampDetailView campDetailView, BindingResult result) throws Exception {
        ValidationErrors.check(result);

        Camp camp = this.service.create(campTransformer.transformToEntityFromDetailView(campDetailView));

        if (campDetailView.getLeadCaseMgr() != null) {
            campTransformer.createLeadCampMgrBusinessEvents(camp, campDetailView);
        }
        if (campDetailView.getCaseMgrOne() != null) {
            campTransformer.createCampMgrOneBusinessEvents(camp, campDetailView);
        }
        if (campDetailView.getCaseMgrTwo() != null) {
            campTransformer.createCampMgrTwoBusinessEvents(camp, campDetailView);
        }

        return campTransformer.transformFromEntityToDetailView(camp);

    }


    @RequestMapping(value = "update")
    @ResponseBody
    public CampDetailView updateCamp(@Valid @RequestBody CampDetailView campDetailView, BindingResult result) throws Exception {
        ValidationErrors.check(result);

        Camp camp = this.service.update(campTransformer.transformToEntityFromDetailView(campDetailView));

        if (campDetailView.getLeadCaseMgr() != null) {
            campTransformer.createLeadCampMgrBusinessEvents(camp, campDetailView);
        }
        if (campDetailView.getCaseMgrOne() != null) {
            campTransformer.createCampMgrOneBusinessEvents(camp, campDetailView);
        }
        if (campDetailView.getCaseMgrTwo() != null) {
            campTransformer.createCampMgrTwoBusinessEvents(camp, campDetailView);
        }

        return campTransformer.transformFromEntityToDetailView(camp);

    }

    @RequestMapping(value = "list")
    @ResponseBody
    public List<CampListView> findAllCamps() throws Exception, AuthorizationException {
        List<CampListView> listOfCamps = new ArrayList<CampListView>();
        //TODO
        //     featureService.isUserAuthorizationForFeature("camps");
        for (Camp camp : this.service.findAll()) {
            if (camp.getStatus() == CampStatus.Active) {
                listOfCamps.add(campTransformer.transformFromEntityToListView(camp));
            }
        }
        return listOfCamps;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCamp(@RequestParam(value = "campId", required = true) Long campId) throws IOException {
        Camp camp = this.service.findById(campId);
        camp.setStatus(CampStatus.Inactive);
        this.service.update(camp);

    }

    @RequestMapping(value = "listByPage")
    @ResponseBody
    public Page<CampListView> findAllUsersPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                    @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                    @RequestParam(value = "properties", required = false) String properties) throws Exception {

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if (direction.isEmpty()) {
            return campTransformer.buildCampsViewPage(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            return campTransformer.buildCampsViewPage(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

    }

    @RequestMapping(value = "queryByPage")
    @ResponseBody
    public Page<CampSearchView> getCampByNamePaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                       @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                       @RequestParam(value = "properties", required = false) String properties, @RequestParam(value = "campName", required = false) String campName) throws Exception {

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if (direction.isEmpty()) {
            return campTransformer.buildCampsViewCampByNamePaginated(new PageRequest(page - 1, size), campName);
        } else {
            Assert.notNull(properties);
            return campTransformer.buildCampsViewCampByNamePaginated(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))), campName);
        }

    }

}
