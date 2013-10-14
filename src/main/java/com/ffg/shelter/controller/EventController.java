package com.ffg.shelter.controller;

import com.ffg.shelter.converter.EventTransformer;
import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.CsbUser;
import com.ffg.shelter.model.Event;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.EventService;
import com.ffg.shelter.view.CampScheduleCheckInView;
import com.ffg.shelter.view.SchedulerCampView;
import com.ffg.shelter.view.SchedulerEventMonthView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping(value = "/api/event")
public class EventController extends ServiceBasedRestController<Event, Long, EventService> {
    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    private EventTransformer eventTransformer;

    @Inject
    @Named("eventService")
    @Override
    public void setService(EventService service) {
        this.service = service;
    }

    @Inject
    @Named("eventTransformer")
    public void setEventTransformer(EventTransformer eventTransformer) {
        this.eventTransformer = eventTransformer;
    }

    @RequestMapping(value = "campCheckIn")
    @ResponseBody
    public void checkInCamp(@RequestBody CampScheduleCheckInView campScheduleCheckInView) throws Exception {
        this.service.create(eventTransformer.transformToEntityFromCampScheduleCheckInView(campScheduleCheckInView));
    }

    @RequestMapping(value = "scheduleVisit")
    @ResponseBody
    public void scheduleCampVisit(@RequestBody CampScheduleCheckInView campScheduleCheckInView) throws Exception {
        this.service.create(eventTransformer.transformToEntityFromCampScheduleCheckInView(campScheduleCheckInView));
    }

    @RequestMapping(value = "allEvent")
    @ResponseBody
    public List<SchedulerEventMonthView> allEvents(@RequestParam(value = "month", required = true) String month) throws IOException, ParseException {
        List<SchedulerEventMonthView> list = eventTransformer.transformFromEntityToCampEventView(this.service.findAllEventForAnMonthAndOrderByEventDate(month), month);
        return list;
    }

    @RequestMapping(value = "campSchedule")
    @ResponseBody
    public List<SchedulerCampView> allCampSchedule(@RequestParam(value = "byUser", required = false) boolean byUser) throws IOException, BusinessException, AuthorizationException, ParseException {
        Long userId = null;
        CsbUser csbUser = adminService.getUser();
        return eventTransformer.transformFromEntityToSchedulerCampView(this.service.findCampVisitedEventsAndCampScheduleCampVisitedEvents());
    }

    public List<Event> allEvent() throws ParseException {

        return this.service.getAllEvents();
    }
}
