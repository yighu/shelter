package com.ffg.shelter.service;

import com.ffg.shelter.model.Camp;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.view.ClientDetailView;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface CampService extends CrudService<Camp, Long> {
    public List<Camp> findByCampName(String campName);

    public Camp findById(Long campId);

    public Camp findIdByCampName(String name);

    void updateCampOccupantCount(ClientDetailView clientDetailView, Client client);
}
