package com.ffg.shelter.controller;

import com.ffg.shelter.converter.FeatureTransformer;
import com.ffg.shelter.model.Feature;
import com.ffg.shelter.service.FeatureService;
import com.ffg.shelter.view.FeatureView;
import com.ffg.shelter.view.UserWorkflowView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/api/feature")
public class FeatureController extends ServiceBasedRestController<Feature, Long, FeatureService> {

    private FeatureTransformer featureTransformer;


    @Inject
    @Named("featureTransformer")
    public void setFeatureTransformer(FeatureTransformer featureTransformer) {
        this.featureTransformer = featureTransformer;
    }

    @Inject
    @Named("featureService")
    @Override
    public void setService(FeatureService service) {
        this.service = service;
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public List<Feature> findAllFeatures() throws IOException {
        return this.service.findAll();
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteFeature(@RequestParam(value = "featureId", required = false) Long featureId) throws IOException {
        this.service.delete(featureId);
    }


    @RequestMapping(value = "details")
    @ResponseBody
    public FeatureView getFeatureDetails(@RequestParam(value = "featureId", required = false) Long featureId) throws IOException {
        return featureTransformer.transformFromEntityToFeatureView(this.service.findById(featureId));
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public FeatureView createFeature(@RequestBody FeatureView featureView) throws Exception {
        return featureTransformer.transformFromEntityToFeatureView(this.service.create(featureTransformer.transformFromViewToEntity(featureView)));
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public FeatureView updateRole(@RequestBody FeatureView featureView) throws Exception {
        return featureTransformer.transformFromEntityToFeatureView(this.service.update(featureTransformer.transformFromViewToEntity(featureView)));
    }

    @RequestMapping(value = "listPrivileges")
    @ResponseBody
    public List<UserWorkflowView> listPrivileges(@RequestParam(value = "featureId", required = false) Long featureId) throws Exception {
        return this.service.privilegeTypeByUserForWorkFlow();
    }
}
