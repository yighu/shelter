package com.ffg.shelter.converter;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.*;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.EventService;
import com.ffg.shelter.view.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Named("eventTransformer")
public class EventTransformerImpl implements EventTransformer {

    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    private EventService eventService;

    @Inject
    @Named("eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    private AdminService service;

    @Inject
    @Named("adminService")
    public void setService(AdminService service) {
        this.service = service;
    }

    private com.ffg.shelter.service.CampService campService;

    @Inject
    @Named("campService")
    public void setCampService(CampService campService) {
        this.campService = campService;
    }

    public Event transformToEntityFromCampScheduleCheckInView(CampScheduleCheckInView campScheduleCheckInView) throws ParseException, BusinessException, AuthorizationException {

        Event event = new Event();
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.roll(Calendar.MONTH, 30);
        event.setEventExpiryDate(new Timestamp(expiryDate.getTimeInMillis()));
        CsbUser csbUser = adminService.getUser();
        event.setEventOwner(csbUser.getId());

	System.out.println("camp sche check in view:"+campScheduleCheckInView.toString());
	System.out.println("scheduledat:"+campScheduleCheckInView.getScheduleDate());
        if (campScheduleCheckInView.isCheckIn()) {
            event.setCampId(campScheduleCheckInView.getCampId());
            event.setEventDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            event.setEventType(EventType.CampVisited);
            event.setComment(campScheduleCheckInView.getComment());
        } else {
            event.setCampId(campService.findIdByCampName(campScheduleCheckInView.getCampName()).getId());
            event.setEventDate(new Timestamp(convertDate(campScheduleCheckInView.getScheduleDate()).getTimeInMillis()));
            event.setEventType(EventType.ScheduleCampVisit);
            event.setComment(campScheduleCheckInView.getComment());

        }
        return event;
    }


    public List<Event> getAllEvents() throws ParseException {
        List<Event> newList = this.eventService.getAllEvents();
        return newList;
    }

    public List<SchedulerEventMonthView> transformFromEntityToCampEventView(List<Event> allEvents, String monthIn) {

        Map<Integer, List<SchedulerEventView>> listEventMap = new HashMap<Integer, java.util.List<SchedulerEventView>>();
        Calendar calendar = Calendar.getInstance();
        int yearIn = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.MONTH, new Integer(monthIn) - 1);
        calendar.set(Calendar.YEAR, yearIn);
        String dayOfMonth = null;
        DateFormat monthFormatter = new SimpleDateFormat("MMMMM");
        dayOfMonth = monthFormatter.format(calendar.getTime());

        List<SchedulerEventMonthView> listOfEventsByDay = new ArrayList<SchedulerEventMonthView>();
        List<SchedulerEventDayView> eventList = new ArrayList<SchedulerEventDayView>();
        for (Event event : allEvents) {
            if ((event.getEventType() == EventType.CampVisited) || (event.getEventType() == EventType.ScheduleCampVisit)) {

		System.out.println("event date:"+event.getEventDate());
                Calendar eventDate = Calendar.getInstance(); //TBD Fixed. this should be the date/time that was set from web
                eventDate.setTimeInMillis(event.getEventDate().getTime());
                int day = eventDate.get(Calendar.DAY_OF_MONTH);
                int hour = eventDate.get(Calendar.HOUR);
                int min= eventDate.get(Calendar.MINUTE);
                int amPm = eventDate.get(Calendar.AM_PM);

                String campName = "";
                campName = getCampNameById(event.getCampId());
                String eventOwner = "";
                if (event.getEventOwner() != null) {
                    CsbUser csbUser = service.findById(event.getEventOwner());
                    eventOwner = csbUser.getFirstName() + "  " + csbUser.getLastName();
                }

                String midDay = "";
                if (amPm == 0) {
                    midDay = "AM";
                } else {
                    midDay = "PM";
                }
                if (hour == 0) {
                    hour = 12;
                }

                String eventDes = hour+":"+min+" " + midDay + "  " + campName + "( " +event.getComment()+ ")-" + " " + eventOwner + " " + "-" + " " + event.getEventType().toString();
                String eventKey = String.valueOf(day);
                String eventDay = dayOfMonth + " " + day;

                if (listEventMap.containsKey(day)) {
                    List<SchedulerEventView> listEvents = listEventMap.get(day);
                    SchedulerEventView schedulerEventView = new SchedulerEventView();
                    schedulerEventView.setCampEvent(eventDes);
                    schedulerEventView.setCampId(event.getCampId());
                    schedulerEventView.setComment(event.getComment());
                    schedulerEventView.setEventDay(eventDay);
                    if (event.getEventType() != null) {
                        schedulerEventView.setEventType(event.getEventType().toString());
                    }
                    listEvents.add(schedulerEventView);
                    listEventMap.put(day, listEvents);
                } else {
                    List<SchedulerEventView> listEvents = new ArrayList<SchedulerEventView>();
                    SchedulerEventView schedulerEventView = new SchedulerEventView();
                    schedulerEventView.setCampEvent(eventDes);
                    listEvents.add(schedulerEventView);
                    schedulerEventView.setEventDay(eventDay);
                    if (event.getEventType() != null) {
                        schedulerEventView.setEventType(event.getEventType().toString());
                    }
                    schedulerEventView.setCampId(event.getCampId());
                    schedulerEventView.setComment(event.getComment());
                    listEventMap.put(day, listEvents);
                }

            }

        }

        List<Integer> eventByDay = new ArrayList<Integer>();
        eventByDay.addAll(listEventMap.keySet());
        Collections.sort(eventByDay);

        for (Integer key : eventByDay) {
            SchedulerEventDayView schedulerEventDayView = new SchedulerEventDayView();
            schedulerEventDayView.setEvents(listEventMap.get(key));
            schedulerEventDayView.setDay(String.valueOf(key));
            eventList.add(schedulerEventDayView);
        }


        SchedulerEventMonthView schedulerEventMonthView = new SchedulerEventMonthView();
        schedulerEventMonthView.setMonth(dayOfMonth);
        schedulerEventMonthView.setEventList(eventList);
        listOfEventsByDay.add(schedulerEventMonthView);
        return listOfEventsByDay;
    }

    private String getCampNameById(Long campId) {
        String campName = null;
        if (campId != null) {
            Camp camp = campService.findById(campId);
            if (camp != null) {
                campName = camp.getName();
            }
        }
        return campName;
    }

    private Camp getCampById(Long campId) {
        Camp camp = null;
        if (campId != null) {
            camp = campService.findById(campId);

        }
        return camp;
    }


    public List<SchedulerCampView> transformFromEntityToSchedulerCampView(List<Event> visitedEventsAndScheduledEvents) throws BusinessException {

        List<SchedulerCampView> listSchedulerCampEvents = new ArrayList<SchedulerCampView>();
        Map<Long, SchedulerCampView> listOfEvent = new HashMap<Long, SchedulerCampView>();
        for (Event event : visitedEventsAndScheduledEvents) {

            Camp camp = getCampById(event.getCampId());
            if (camp.getStatus() == CampStatus.Active) {
                SchedulerCampView schedulerCampView = null;
                CsbUser csbUser = adminService.findById(event.getEventOwner());
                String name = csbUser.getFirstName() + " " + csbUser.getLastName();
                if (listOfEvent.containsKey(camp.getId())) {
                    schedulerCampView = listOfEvent.get(camp.getId());
                } else {
                    schedulerCampView = new SchedulerCampView();
                    schedulerCampView.setCampName(camp.getName());
                    schedulerCampView.setViolent(camp.getBadge() == CampBadge.Violent);
                }
                if (event.getEventType() == EventType.CampVisited) {
                    schedulerCampView.setLastVisitedCaseManager(name);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(event.getEventDate().getTime());
                    int values = calendar.get(Calendar.MONTH) + 1;
                    schedulerCampView.setLastVisitedDate(values + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
                }
                if (event.getEventType() == EventType.ScheduleCampVisit) {
                    schedulerCampView.setNextVisingCaseManager(name);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(event.getEventDate().getTime());
                    int values = calendar.get(Calendar.MONTH) + 1;
                    schedulerCampView.setNextVisitDate(values + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
                }
                listOfEvent.put(camp.getId(), schedulerCampView);

            }
        }
        listSchedulerCampEvents.addAll(listOfEvent.values());
        return listSchedulerCampEvents;
    }


    private Calendar convertDate(String date) throws ParseException {

        Calendar eDate = Calendar.getInstance();
        int hour = eDate.get(Calendar.HOUR);
        eDate.clear();
	System.out.println("input date:"+date);
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
            eDate.setTime(formatter.parse(date));
        }
	System.out.println("output date:"+DateFormat.getDateInstance().format(eDate.getTime()));

        //eDate.set(Calendar.HOUR_OF_DAY, hour);
        return eDate;

    }

    public List<UserWorkflowView> getPrivilegeForWorkFlow() {
        return null;
    }
}
