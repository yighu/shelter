package com.ffg.shelter.converter;

import com.ffg.shelter.model.*;
import com.ffg.shelter.repository.CampStatsRepository;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.EventService;
import com.ffg.shelter.view.CampCaseMangerView;
import com.ffg.shelter.view.CampDetailView;
import com.ffg.shelter.view.CampListView;
import com.ffg.shelter.view.CampSearchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Named("campTransformer")
public class CampTransformerImpl implements CampTransformer {
    private AdminService service;

    @Inject
    @Named("adminService")
    public void setService(AdminService service) {
        this.service = service;
    }

    private CampService campService;

    @Inject
    @Named("campService")
    public void setCampService(CampService campService) {
        this.campService = campService;
    }

    private EventService eventService;

    @Inject
    @Named("eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }


    private CampStatsRepository campStatsRepository;

    @Inject
    @Named("campStatsRepository")
    public void setCampStatsRepository(CampStatsRepository campStatsRepository) {
        this.campStatsRepository = campStatsRepository;
    }

    public CampSearchView transformFromEntityToSearchView(Camp camp) {
        CampSearchView campSearchView = new CampSearchView();
        campSearchView.setId(camp.getId() + "");
        campSearchView.setLatitude(camp.getLatitude());
        campSearchView.setLongitude(camp.getLongitude());
        campSearchView.setLocation(camp.getStreetAddress());
        campSearchView.setName(camp.getName());
        return campSearchView;
    }

    public CampListView transformFromEntityToListView(Camp camp) throws ParseException {
        CampListView campListView = new CampListView();
        if (camp.getBadge() != null) {
            campListView.setBadge(camp.getBadge().name());
        }
        Calendar calendar = camp.getScrubDate();
        if (calendar != null) {
            campListView.setCampScrubDate(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
        }
        calendar = camp.getSetUpDate();
        if (calendar != null) {
            campListView.setSetUpDate(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
        }
        if (camp.getType() != null) {
            campListView.setCampType(camp.getType().toString());
        }
        Set<CampStats> campStatsList = camp.getCampStats();
        for (CampStats campStats : campStatsList) {
            if (campStats.getCampStat() == CampStat.Female) {
                campListView.setCampFemaleCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Male) {
                campListView.setCampMaleCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Children) {
                campListView.setCampChildCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Structure) {
                campListView.setCampStructureCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Animal) {
                campListView.setCampPetCount(campStats.getCount() + "");
            }
        }

        int clientCount = 0;

        if (campListView.getCampMaleCount() != null) {
            clientCount += new Integer(campListView.getCampMaleCount());
        }

        if (campListView.getCampFemaleCount() != null) {
            clientCount += new Integer(campListView.getCampFemaleCount());
        }

        if (campListView.getCampChildCount() != null) {
            clientCount += new Integer(campListView.getCampChildCount());
        }
        campListView.setClientCount(clientCount + "");
        campListView.setLatitude(camp.getLatitude());
        campListView.setLongitude(camp.getLongitude());
        campListView.setLocation(camp.getStreetAddress());
        campListView.setName(camp.getName());
        campListView.setId(camp.getId() + "");
        if (camp.getStatus() != null) {
            campListView.setStatus(camp.getStatus().name());
        }
        campListView.setDescription(camp.getDescription());

        List<Event> lastVisitedEvents = eventService.findCampScheduleCampVisitedForACampEvents(camp.getId());
        for (Event event : lastVisitedEvents) {
            if (event.getEventType() == EventType.CampVisited) {
                String strdate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                strdate = sdf.format(event.getEventDate());
                campListView.setLastVisitedDate(strdate);
                break;
            }
        }

        return campListView;
    }

    public CampDetailView transformFromEntityToDetailView(Camp camp) throws ParseException {

        CampDetailView campDetailView = new CampDetailView();
        if (camp.getBadge() != null) {
            campDetailView.setBadge(camp.getBadge().name());
        }
        Calendar calendar = camp.getScrubDate();
        if (calendar != null) {
            String strdate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            strdate = sdf.format(calendar.getTime());
            campDetailView.setCampScrubDate(strdate);
            campDetailView.setCampScrubDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
            campDetailView.setCampScrubMonth(calendar.get(Calendar.MONTH) + "");
            campDetailView.setCampScrubYear(calendar.get(Calendar.YEAR) + "");
        }


        calendar = camp.getSetUpDate();
        if (calendar != null) {
            String strdate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            strdate = sdf.format(calendar.getTime());
            campDetailView.setSetUpDate(strdate);
            campDetailView.setSetUpDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
            campDetailView.setSetUpMonth(calendar.get(Calendar.MONTH) + "");
            campDetailView.setSetUpYear(calendar.get(Calendar.YEAR) + "");
        }
        if (camp.getType() != null) {
            campDetailView.setCampType(camp.getType().toString());
        }
        Set<CampStats> campStatsList = camp.getCampStats();
        for (CampStats campStats : campStatsList) {
            if (campStats.getCampStat() == CampStat.Female) {
                campDetailView.setCampFemaleCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Male) {
                campDetailView.setCampMaleCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Children) {
                campDetailView.setCampChildCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Structure) {
                campDetailView.setCampStructureCount(campStats.getCount() + "");
            }
            if (campStats.getCampStat() == CampStat.Animal) {
                campDetailView.setCampPetCount(campStats.getCount() + "");
            }
        }

        int clientCount = 0;

        if (campDetailView.getCampMaleCount() != null) {
            clientCount += new Integer(campDetailView.getCampMaleCount());
        }

        if (campDetailView.getCampFemaleCount() != null) {
            clientCount += new Integer(campDetailView.getCampFemaleCount());
        }

        if (campDetailView.getCampChildCount() != null) {
            clientCount += new Integer(campDetailView.getCampChildCount());
        }
        campDetailView.setDescription(camp.getDescription());
        campDetailView.setClientCount(clientCount + "");
        if (camp.getStatus() != null) {
            campDetailView.setStatus(camp.getStatus().name());
        }
        campDetailView.setLatitude(camp.getLatitude());
        campDetailView.setLongitude(camp.getLongitude());
        campDetailView.setLocation(camp.getStreetAddress());
        campDetailView.setName(camp.getName());
        campDetailView.setId(camp.getId() + "");
        List<CsbUser> userList = service.findAll();
        List<CampCaseMangerView> listOfCaseMgr = new ArrayList<CampCaseMangerView>();
        for (CsbUser csbUser : userList) {
            if ((csbUser.getRole() == UserRole.CaseManager) || (csbUser.getRole() == UserRole.Admin)) {
                if (csbUser.getExpiryDate().after(Calendar.getInstance())) {
                    CampCaseMangerView campCaseMangerView = new CampCaseMangerView();
                    campCaseMangerView.setName(csbUser.getFirstName() + " " + csbUser.getLastName());
                    listOfCaseMgr.add(campCaseMangerView);
                }
            }
        }
        campDetailView.setListOfCaseMgr(listOfCaseMgr);
        if (camp.getAlert() != null) {
            campDetailView.setAlert(camp.getAlert().toString());
        }


        List<Event> lastVisitedEvents = eventService.findCampScheduleCampVisitedForACampEvents(camp.getId());
        for (Event event : lastVisitedEvents) {
            if (event.getEventType() == EventType.CampVisited) {
                String strdate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                strdate = sdf.format(event.getEventDate());
                campDetailView.setLastVisitedDate(strdate);
                break;
            }
        }

        List<Event> campManagerEvents = eventService.findCampManagerForACampEvents(camp.getId());
        for (Event event : campManagerEvents) {
            if (event.getEventType() == EventType.AssignLeadCampMgr) {
                campDetailView.setLeadCaseMgr(getUserFullName(event));
            }
            if (event.getEventType() == EventType.AssignCampMgrOne) {
                campDetailView.setCaseMgrOne(getUserFullName(event));
            }
            if (event.getEventType() == EventType.AssignCampMgrTwo) {
                campDetailView.setCaseMgrTwo(getUserFullName(event));
            }
        }

        campDetailView.setActive(camp.getStatus() == CampStatus.Active);
        campDetailView.setId(camp.getId().toString());
        return campDetailView;

    }

    private String getUserFullName(Event event) {
        String userFullName = null;
        if (event != null) {
            CsbUser csbUser = service.findById(event.getEventOwner());

            if (csbUser != null) {
                userFullName = csbUser.getFirstName() + " " + csbUser.getLastName();
            }
        }
        return userFullName;
    }


    public Camp transformToEntityFromDetailView(CampDetailView campDetailView) throws ParseException {
        Camp camp = new Camp();
        if (campDetailView.getBadge() != null) {
            camp.setBadge(CampConverter.convertCampBadge(campDetailView.getBadge()));
        }
        camp.setScrubDate(convertDate(campDetailView.getCampScrubDate()));
        Calendar setupDate = convertDate(campDetailView.getSetUpDay(), campDetailView.getSetUpMonth(), campDetailView.getSetUpYear());
        camp.setSetUpDate(convertDate(campDetailView.getSetUpDate()));
        if (campDetailView.getAlert() != null) {
            camp.setAlert(CampConverter.convertCampAlert(campDetailView.getAlert()));
        }
        if (campDetailView.getCampType() != null) {
            camp.setType(CampConverter.convertCampType(campDetailView.getCampType()));
        }

        camp.setDescription(campDetailView.getDescription());
        if (campDetailView.getId() != null) {
            camp.setId(new Long(campDetailView.getId()));
        }
        if (campDetailView.getStatus() != null) {
            camp.setStatus(CampConverter.convertCampStatus(campDetailView.getStatus()));
        } else {
            camp.setStatus(CampStatus.Active);
        }

        Set<CampStats> campStatsList = camp.getCampStats();
        if (campDetailView.getCampFemaleCount() != null) {
            CampStats campStats = new CampStats();
            campStats.setCampStat(CampStat.Female);
            campStats.setCount(new Integer(campDetailView.getCampFemaleCount()));
            campStatsList.add(campStatsRepository.save(campStats));
        }
        if (campDetailView.getCampMaleCount() != null) {
            CampStats campStats = new CampStats();
            campStats.setCampStat(CampStat.Male);
            campStats.setCount(new Integer(campDetailView.getCampMaleCount()));
            campStatsList.add(campStatsRepository.save(campStats));
        }
        if (campDetailView.getCampChildCount() != null) {
            CampStats campStats = new CampStats();
            campStats.setCampStat(CampStat.Children);
            campStats.setCount(new Integer(campDetailView.getCampChildCount()));
            campStatsList.add(campStatsRepository.save(campStats));
        }
        if (campDetailView.getCampPetCount() != null) {
            CampStats campStats = new CampStats();
            campStats.setCampStat(CampStat.Animal);
            campStats.setCount(new Integer(campDetailView.getCampPetCount()));
            campStatsList.add(campStatsRepository.save(campStats));
        }
        if (campDetailView.getCampStructureCount() != null) {
            CampStats campStats = new CampStats();
            campStats.setCampStat(CampStat.Structure);
            campStats.setCount(new Integer(campDetailView.getCampStructureCount()));
            campStatsList.add(campStatsRepository.save(campStats));
        }

        Set<CsbUser> users = new HashSet<CsbUser>();

        if (campDetailView.getLeadCaseMgr() != null) {
            users.add(service.findById(getUser(campDetailView.getLeadCaseMgr())));
        }
        if (campDetailView.getCaseMgrOne() != null) {
            users.add(service.findById(getUser(campDetailView.getCaseMgrOne())));
        }
        if (campDetailView.getCaseMgrTwo() != null) {
            users.add(service.findById(getUser(campDetailView.getCaseMgrTwo())));
        }
        camp.setUsers(users);
        camp.setLatitude(campDetailView.getLatitude());
        camp.setLongitude(campDetailView.getLongitude());
        camp.setStreetAddress(campDetailView.getLocation());
        camp.setName(campDetailView.getName());

        return camp;
    }


    public void createLeadCampMgrBusinessEvents(Camp camp, CampDetailView campDetailView) {
        Event event = new Event();
        event.setCampId(camp.getId());
        event.setEventDate(new Timestamp(camp.getSetUpDate().getTimeInMillis()));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.roll(Calendar.MONTH, 3);
        event.setEventExpiryDate(new Timestamp(expiryDate.getTimeInMillis()));
        event.setEventOwner(getUser(campDetailView.getLeadCaseMgr()));
        event.setEventType(EventType.AssignLeadCampMgr);
        eventService.create(event);
    }

    public void createCampMgrOneBusinessEvents(Camp camp, CampDetailView campDetailView) {
        Event event = new Event();
        event.setCampId(camp.getId());
        event.setEventDate(new Timestamp(camp.getSetUpDate().getTimeInMillis()));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.roll(Calendar.MONTH, 3);
        event.setEventExpiryDate(new Timestamp(expiryDate.getTimeInMillis()));
        event.setEventOwner(getUser(campDetailView.getCaseMgrOne()));
        event.setEventType(EventType.AssignCampMgrOne);
        eventService.create(event);
    }

    public void createCampMgrTwoBusinessEvents(Camp camp, CampDetailView campDetailView) {
        Event event = new Event();
        event.setCampId(camp.getId());
        //event.setEventDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        event.setEventDate(new Timestamp(camp.getSetUpDate().getTimeInMillis()));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.roll(Calendar.MONTH, 3);
        event.setEventExpiryDate(new Timestamp(expiryDate.getTimeInMillis()));
        event.setEventOwner(getUser(campDetailView.getCaseMgrTwo()));
        event.setEventType(EventType.AssignCampMgrTwo);
        eventService.create(event);
    }

    private Calendar convertDate(String day, String month, String year) {
        if (day == null || month == null || year == null) {
            return Calendar.getInstance();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return calendar;
    }

    private Long getUser(String leadCaseMgr) {
        String[] splits = leadCaseMgr.split(" ");
        String lastName = null;
        String firstName = null;
        if (splits != null) {
            firstName = splits[0];
            lastName = splits[1];
        }

        List<CsbUser> userList = service.findAll();
        if (leadCaseMgr != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getLastName().equalsIgnoreCase(lastName)) {
                    return csbUser.getId();
                }
            }
        }
        return new Long(1);
    }

    private CsbUser getUserByEmail(String userEmail) {
        List<CsbUser> userList = service.findAll();
        if (userEmail != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getEmail().equalsIgnoreCase(userEmail)) {
                    return csbUser;
                }
            }
        }
        return null;
    }


    private Calendar convertDate(String date) throws ParseException {

        Calendar eDate = Calendar.getInstance();
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            eDate.setTime(formatter.parse(date));
            return eDate;
        }
        return null;

    }

    public Page<CampListView> buildCampsViewPage(PageRequest pageRequest) throws Exception {
        List<CampListView> listOfClients = new ArrayList<CampListView>();
        Page<Camp> pageOfCamps = null;
        PageRequest pageRequestResponse = null;
        pageOfCamps = this.campService.findAll(pageRequest);

        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfCamps.getNumber(), pageOfCamps.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfCamps.getNumber(), pageOfCamps.getSize(), pageOfCamps.getSort());
        }

        if (pageOfCamps != null) {
            for (Camp camp : pageOfCamps.getContent()) {
                listOfClients.add(transformFromEntityToListView(camp));
            }
        }

        PageImpl page = new PageImpl(listOfClients, pageRequestResponse, pageOfCamps.getTotalElements());

        return page;
    }

    //TODO  have to implement page with search, this is just a template.
    public Page<CampSearchView> buildCampsViewCampByNamePaginated(PageRequest pageRequest, String campName) throws Exception {
        List<CampSearchView> listOfClients = new ArrayList<CampSearchView>();
        if (campName != null) {
            campName = '%' + campName.toUpperCase().replaceAll("\\s", "") + '%';
        }
        List<Camp> listOfCamps = this.campService.findByCampName(campName);
        Page<Camp> pageOfCamps = null;
        PageRequest pageRequestResponse = null;


        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfCamps.getNumber(), pageOfCamps.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfCamps.getNumber(), pageOfCamps.getSize(), pageOfCamps.getSort());
        }

        if (pageOfCamps != null) {
            for (Camp camp : listOfCamps) {
                listOfClients.add(transformFromEntityToSearchView(camp));
            }
        }

        PageImpl page = new PageImpl(listOfClients, pageRequestResponse, pageOfCamps.getTotalElements());

        return page;
    }
}
