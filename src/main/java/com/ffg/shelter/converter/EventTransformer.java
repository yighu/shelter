package com.ffg.shelter.converter;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.Event;
import com.ffg.shelter.view.CampScheduleCheckInView;
import com.ffg.shelter.view.SchedulerCampView;
import com.ffg.shelter.view.SchedulerEventMonthView;
import com.ffg.shelter.view.UserWorkflowView;

import java.text.ParseException;
import java.util.List;


public interface EventTransformer {

    Event transformToEntityFromCampScheduleCheckInView(CampScheduleCheckInView campScheduleCheckInView) throws ParseException, BusinessException, AuthorizationException;

    List<SchedulerEventMonthView> transformFromEntityToCampEventView(List<Event> all, String month);

    List<SchedulerCampView> transformFromEntityToSchedulerCampView(List<Event> visitedEventsAndScheduledEvents) throws BusinessException;

    List<Event> getAllEvents() throws ParseException;

    List<UserWorkflowView> getPrivilegeForWorkFlow();
}
