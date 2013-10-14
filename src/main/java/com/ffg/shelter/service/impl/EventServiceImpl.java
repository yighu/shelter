package com.ffg.shelter.service.impl;

import com.ffg.shelter.converter.EventTransformer;
import com.ffg.shelter.model.Event;
import com.ffg.shelter.repository.EventRepository;
import com.ffg.shelter.service.EventService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;


@Transactional
@Named("eventService")
public class EventServiceImpl extends CrudServiceImpl<Event, Long, EventRepository> implements EventService {
    @Override
    @Inject
    public void setRepository(EventRepository eventRepository) {
        super.setRepository(eventRepository);
    }

    private EventTransformer eventTransformer;

    @Inject
    @Named("eventTransformer")
    public void setEventTransformer(EventTransformer eventTransformer) {
        this.eventTransformer = eventTransformer;
    }

    public List<Event> findAllEventForAnMonthAndOrderByEventDate(String month) throws ParseException {
        int value = 0;
        if (month == null) {
            value = new Integer(1);
        } else {
            value = new Integer(month);
        }
        Timestamp lowDateRange = convertDate(value, 1);
        Timestamp highDateRange = convertDate(value, 30);
        return this.repository.findAllEventForAnMonthAndOrderByEventDate(lowDateRange, highDateRange);
    }

    public List<Event> findCampVisitedEventsAndCampScheduleCampVisitedEvents() throws ParseException {
        int value = getMonth();
        Timestamp lowDateRange = convertDate(1, 1);
        Timestamp highDateRange = convertDate(value, 180);
        System.out.println("lowDateRange=" + lowDateRange);
        System.out.println("highDateRange=" + highDateRange);
        return this.repository.findCampVisitedEventsAndCampScheduleCampVisitedEvents(lowDateRange, highDateRange);
    }


    public List<Event> findCampScheduleCampVisitedForACampEvents(Long campId) throws ParseException {
        int value = getMonth();
        Timestamp lowDateRange = convertDate(1, 1);
        Timestamp highDateRange = convertDate(value, 180);
        return this.repository.findCampScheduleCampVisitedForACampEvents(campId, lowDateRange, highDateRange);
    }

    @Override
    public List<Event> findCampManagerForACampEvents(Long campId) throws ParseException {
        int value = getMonth();
        Timestamp lowDateRange = convertDate(1, 1);
        Timestamp highDateRange = convertDate(value, 180);
        return this.repository.findCampManagerForACampEvents(campId, lowDateRange, highDateRange);
    }

    public List<Event> getAllEvents() throws ParseException {
        int value = getMonth();
        Timestamp lowDateRange = convertDate(1, 1);
        Timestamp highDateRange = convertDate(value, 180);

        return this.repository.getAllEvents(lowDateRange, highDateRange);
    }

    private Timestamp convertDate(Integer month, int numberOfDays) throws ParseException {
        Calendar eDate = Calendar.getInstance();
        eDate.set(Calendar.getInstance().get(Calendar.YEAR), month - 1, numberOfDays);

        return new Timestamp(eDate.getTimeInMillis());

    }

    private int getMonth() {
        int value = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return value;
    }
}
