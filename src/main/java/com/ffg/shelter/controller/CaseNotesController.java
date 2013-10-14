package com.ffg.shelter.controller;


import com.ffg.shelter.converter.CaseNotesTransformer;
import com.ffg.shelter.converter.ClientTransformer;
import com.ffg.shelter.model.CaseNotes;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.service.CaseNotesService;
import com.ffg.shelter.view.CaseNotesView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/api/notes")
public class CaseNotesController extends ServiceBasedRestController<CaseNotes, Long, CaseNotesService> {

    private CaseNotesTransformer caseNotesTransformer;
    private ClientTransformer clientTransformer;

    @Inject
    @Named("caseNotesService")
    @Override
    public void setService(CaseNotesService service) {
        this.service = service;
    }


    @Inject
    @Named("caseNotesTransformer")
    public void setCaseNotesTransformer(CaseNotesTransformer caseNotesTransformer) {
        this.caseNotesTransformer = caseNotesTransformer;
    }


    @RequestMapping(value = "clientDetails")
    @ResponseBody
    public Client getClientDetails(@RequestParam(value = "clientId", required = false) Long clientId) throws IOException {
        return this.caseNotesTransformer.getClientById(clientId);

    }


    @RequestMapping(value = "noteDetails")
    @ResponseBody
    public CaseNotesView findNote(@RequestParam(value = "noteId", required = false) Long noteId) throws IOException {

        CaseNotes note = this.service.findCaseNote(noteId);
        return this.caseNotesTransformer.transformToEntityFromCaseNotes(note);
    }


    @RequestMapping(value = "add")
    @ResponseBody
    public CaseNotesView createNotes(@RequestBody CaseNotesView caseNotesView) throws IOException {
        CaseNotes caseNotes = caseNotesTransformer.transformToEntityFromCaseNotesView(caseNotesView);
        return caseNotesTransformer.transformToEntityFromCaseNotes(this.service.create(caseNotes));

    }

    @RequestMapping(value = "notes")
    @ResponseBody
    public List<CaseNotesView> findAllNotes(@RequestParam(value = "clientId", required = false) Long clientId) throws IOException {
        List<CaseNotesView> listNotes = new ArrayList<CaseNotesView>();
        for (CaseNotes caseNotes : this.service.findNotes(clientId)) {
            listNotes.add(caseNotesTransformer.transformToEntityFromCaseNotes(caseNotes));
        }
        return listNotes;

    }


    @RequestMapping(value = "update")
    @ResponseBody
    public CaseNotesView updateNotes(@RequestBody CaseNotesView caseNotesView) throws Exception {
        CaseNotes caseNotes = caseNotesTransformer.transformToEntityFromCaseNotesView(caseNotesView);
        return caseNotesTransformer.transformToEntityFromCaseNotes(this.service.update(caseNotes));
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public void deleteNotes(@RequestParam(value = "caseNotesId", required = true) Long caseNotesId) throws Exception {
        this.service.delete(caseNotesId);
    }

}