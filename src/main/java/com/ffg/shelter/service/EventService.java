package com.ffg.shelter.service;

import com.ffg.shelter.model.Event;
import org.resthub.common.service.CrudService;

import java.text.ParseException;
import java.util.List;


public interface EventService extends CrudService<Event, Long> {

    List<Event> findAllEventForAnMonthAndOrderByEventDate(String month) throws ParseException;

    List<Event> findCampVisitedEventsAndCampScheduleCampVisitedEvents() throws ParseException;

    List<Event> findCampScheduleCampVisitedForACampEvents(Long campId) throws ParseException;

    List<Event> findCampManagerForACampEvents(Long campId) throws ParseException;

    List<Event> getAllEvents() throws ParseException;
}
