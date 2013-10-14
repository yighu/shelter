package com.ffg.shelter.service;

import com.ffg.shelter.model.CaseNotes;
import com.ffg.shelter.view.CaseNotesView;
import org.resthub.common.service.CrudService;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Amanda
 * Date: 8/11/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CaseNotesService extends CrudService<CaseNotes, Long>{

    public CaseNotes findNotesById(Long clientId);

    public List<CaseNotes> findNotes(Long id);

    public CaseNotes findCaseNote(Long caseId);
}
