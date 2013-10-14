package com.ffg.shelter.converter;


import com.ffg.shelter.model.CaseNotes;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.CaseNotesService;
import com.ffg.shelter.service.ClientService;
import com.ffg.shelter.view.CaseNotesView;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;


@Named("caseNotesTransformer")
public class CaseNotesTransformerImpl implements CaseNotesTransformer {

    private CaseNotesService caseNotesService;
    private AdminService adminService;
    private ClientService clientService;


    @Inject
    @Named("caseNotesService")
    public void setCaseNotesService(CaseNotesService service) {
        this.caseNotesService = service;
    }


    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Inject
    @Named("clientService")
    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    public CaseNotes transformToEntityFromCaseNotesView(CaseNotesView caseNotesView) {
        CaseNotes caseNotes = new CaseNotes();
        Date createdDate = convertDate(caseNotesView.getCreatedDate());
        caseNotes.setCreatedDate(createdDate);
        caseNotes.setCaseMng(caseNotesView.getCaseMngName());
        caseNotes.setNote(caseNotesView.getNote());
        Long clientId = new Long(caseNotesView.getClientId());
        caseNotes.setClientId(clientId);
        if (caseNotesView.getHousingStage() != null) {
            caseNotes.setHousingStage(ClientConverter.convertHousingStage(caseNotesView.getHousingStage()));
        }
        if (caseNotesView.getId() != null) {
            Long id = new Long(caseNotesView.getId());
            caseNotes.setId(id);
        }
        return caseNotes;

    }

    public CaseNotesView transformToEntityFromCaseNotes(CaseNotes caseNotes) {
        CaseNotesView caseNotesView = new CaseNotesView();
        caseNotesView.setCaseMngName(caseNotes.getCaseMng());
        caseNotesView.setNote(caseNotes.getNote());
        if (caseNotes.getHousingStage() != null) {
            String caseNotesStage = caseNotes.getHousingStage().toString();
            caseNotesView.setHousingStage(caseNotesStage);

        }
        caseNotesView.setClientName(getClientNameById(caseNotes.getClientId()));
        caseNotesView.setClientId(caseNotes.getClientId().toString());
        Date caseDate = caseNotes.getCreatedDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(caseDate);
        if (caseDate != null) {
            caseNotesView.setCreatedDate(caseDate.getTime() + "");

        }
        if (caseNotes.getId() != null)
            caseNotesView.setId(caseNotes.getId().toString());
        return caseNotesView;
    }

    private Date convertDate(String date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            Date newDate = calendar.getTime();
            return newDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Long(date));
        Date newDate = calendar.getTime();

        return newDate;
    }

    private String getClientNameById(Long id) {
        String clientName = clientService.findClientById(id).getFirstName() + " " + clientService.findClientById(id).getLastName();
        return clientName;
    }

    private Long getClientIdByName(String name) {
        Long clientId = clientService.findIdByClientName(name).getId();
        return clientId;
    }


    public Client getClientById(Long id) {
        Client client = clientService.findClientById(id);
        return client;
    }

    private Date convertDateFromCalendar(Calendar date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            Date newDate = calendar.getTime();
            return newDate;
        }
        Date newDate = date.getTime();

        return newDate;
    }

}
