package com.ffg.shelter.repository;

import com.ffg.shelter.model.CaseNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CaseNotesRepository extends JpaRepository<CaseNotes, Long> {

    @Query("select u from CaseNotes u where u.clientId = :clientId order by u.createdDate DESC")
    CaseNotes findNotesByClientId(@Param("clientId") Long clientId);

    @Query("select u from CaseNotes u where u.id = :id")
    CaseNotes findCaseNote(@Param("id") Long id);

    @Query("select u from CaseNotes u where u.clientId = :clientId order by u.createdDate DESC")
    List<CaseNotes> findNotes(@Param("clientId") Long clientId);

}
