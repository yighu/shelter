package com.ffg.shelter.converter;


import com.ffg.shelter.model.CaseNotes;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.view.CaseNotesView;


public interface CaseNotesTransformer {

    CaseNotes transformToEntityFromCaseNotesView(CaseNotesView caseNotesView);

    CaseNotesView transformToEntityFromCaseNotes(CaseNotes CaseNotes);

    Client getClientById(Long id);

}
