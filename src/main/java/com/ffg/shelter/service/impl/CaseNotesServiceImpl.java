package com.ffg.shelter.service.impl;

import com.ffg.shelter.converter.CaseNotesTransformer;
import com.ffg.shelter.model.CaseNotes;
import com.ffg.shelter.repository.CaseNotesRepository;
import com.ffg.shelter.service.CaseNotesService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Transactional
@Named("caseNotesService")
public class CaseNotesServiceImpl extends CrudServiceImpl<CaseNotes, Long, CaseNotesRepository> implements CaseNotesService {

    private CaseNotesTransformer caseNotesTransformer;

    @Override
    @Inject
    public void setRepository(CaseNotesRepository caseNotesRepository) {
        super.setRepository(caseNotesRepository);
    }

    @Inject
    @Named("caseNotesTransformer")
    public void setNewsFeedTransformer(CaseNotesTransformer caseNotesTransformer) {
        this.caseNotesTransformer = caseNotesTransformer;
    }

    public CaseNotes findNotesById(Long clientId) {
        CaseNotes caseNotes = this.repository.findNotesByClientId(clientId);
        return caseNotes;
    }

    public CaseNotes findCaseNote(Long id) {
        CaseNotes caseNotes = this.repository.findCaseNote(id);
        return caseNotes;
    }

    public List<CaseNotes> findNotes(Long clientId) {
        List<CaseNotes> caseNotes = this.repository.findNotes(clientId);
        return caseNotes;
    }


}
