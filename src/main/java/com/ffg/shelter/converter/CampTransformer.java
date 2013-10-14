package com.ffg.shelter.converter;

import com.ffg.shelter.model.Camp;
import com.ffg.shelter.view.CampDetailView;
import com.ffg.shelter.view.CampListView;
import com.ffg.shelter.view.CampSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;


public interface CampTransformer {
    public CampSearchView transformFromEntityToSearchView(Camp camp);

    public CampListView transformFromEntityToListView(Camp camp) throws ParseException;

    public CampDetailView transformFromEntityToDetailView(Camp camp) throws ParseException;

    public Camp transformToEntityFromDetailView(CampDetailView campDetailView) throws ParseException;

    void createLeadCampMgrBusinessEvents(Camp camp, CampDetailView campDetailView);

    void createCampMgrOneBusinessEvents(Camp camp, CampDetailView campDetailView);

    void createCampMgrTwoBusinessEvents(Camp camp, CampDetailView campDetailView);

    Page<CampListView> buildCampsViewPage(PageRequest pageRequest) throws Exception;

    Page<CampSearchView> buildCampsViewCampByNamePaginated(PageRequest pageRequest, String campName) throws Exception;


}
