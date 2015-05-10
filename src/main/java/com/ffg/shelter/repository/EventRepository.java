package com.ffg.shelter.repository;

import com.ffg.shelter.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select u from Event u where u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by u.eventDate desc")
    List<Event> findAllEventForAnMonthAndOrderByEventDate(@Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);

    @Query("select u from Event u where ( u.eventType = 'CampVisited' or  u.eventType = 'ScheduleCampVisit') and u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by u.eventDate desc ")
    List<Event> findCampVisitedEventsAndCampScheduleCampVisitedEvents(@Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);


    @Query("select u from Event u where (u.eventType = 'AssignLeadCampMgr' or u.eventType = 'AssignCampMgrOne' or u.eventType = 'AssignCampMgrTwo') and u.campId = :campId and u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by eventDate desc")
    List<Event> findCampManagerEventsForACamp(@Param("campId") Long campId, @Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);

    @Query("select u from Event u where u.eventType = 'CampVisited' and u.campId = :campId and u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by u.eventDate desc")
    List<Event> findCampScheduleCampVisitedForACampEvents(@Param("campId") Long campId, @Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);

    @Query("select u from Event u where u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by u.eventDate desc ")
    List<Event> getAllEvents(@Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);

    @Query("select u from Event u where ( u.eventType = 'AssignLeadCampMgr' or  u.eventType = 'AssignCampMgrOne' or  u.eventType = 'AssignCampMgrTwo')  and u.campId = :campId and u.eventDate >= :lowDateRange and u.eventDate <= :highDateRange order by u.eventDate desc")
    List<Event> findCampManagerForACampEvents(@Param("campId") Long campId, @Param("lowDateRange") Timestamp lowDateRange, @Param("highDateRange") Timestamp highDateRange);

}
