package com.ffg.shelter.converter;

import com.ffg.shelter.model.*;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.EventService;
import com.ffg.shelter.service.NewsFeedService;
import com.ffg.shelter.view.NewsFeedView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Named("newsFeedTransformer")
public class NewsFeedTransformerImpl implements NewsFeedTransformer {

    private NewsFeedService newsFeedService;
    private EventService eventService;
    private CampService campService;
    private AdminService adminService;

    //  NewsFeedView transformEventtoView(Event);


    @Inject
    @Named("newsFeedService")
    public void setNewsFeedService(NewsFeedService newsService) {
        this.newsFeedService = newsService;
    }

    @Inject
    @Named("eventService")
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }


    @Inject
    @Named("campService")
    public void setCampService(CampService service) {
        this.campService = service;
    }


    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    public NewsFeedView transformEventToNewsFeed(Event event) {
        NewsFeedView newsFeedView = new NewsFeedView();


        Long id = event.getId();

        newsFeedView.setEventId(id.toString());
        newsFeedView.setId(id.toString());
        newsFeedView.setCampName(getCampNameById(event.getCampId()));
        Calendar eventDate = Calendar.getInstance();
        eventDate.setTimeInMillis(event.getEventDate().getTime());
        if (eventDate != null) {
            newsFeedView.setCreatedByDate((eventDate.get(Calendar.MONTH) + "/" + eventDate.get(Calendar.DAY_OF_MONTH) + "/" + eventDate.get(Calendar.YEAR)));
        }
        newsFeedView.setComments("" + event.getEventType());
        return newsFeedView;
    }

    public NewsFeed transformToEntityFromNewsFeedView(NewsFeedView newsFeedView) {
        NewsFeed newsfeed = new NewsFeed();
        newsfeed.setMessageType(NewsFeedConverter.convertNewsType(newsFeedView.getMessageType()));
        Date createdDate = convertDate(newsFeedView.getCreatedByDate());
        newsfeed.setCreatedDate(createdDate);
        newsfeed.setComments(newsFeedView.getComments());
        expireDate(newsfeed);
        String campName = newsFeedView.getCampName();
        Long id = campService.findIdByCampName(campName).getId();
        newsfeed.setCampId(id);

        if (newsFeedView.getEventId() != null) {
            newsfeed.setEventId(Long.parseLong(newsFeedView.getEventId()));
        }
        if (newsFeedView.getCreatedBy() != null) {
            newsfeed.setCreatedBy(newsFeedView.getCreatedBy());
        }
        return newsfeed;

    }

    public NewsFeedView transformToEntityFromNews(NewsFeed news) {
        NewsFeedView newsFeedView = new NewsFeedView();
        newsFeedView.setId((news.getId().toString()));
        newsFeedView.setEventId(news.getEventId().toString());
        newsFeedView.setComments(news.getComments());
        newsFeedView.setCreatedBy(news.getCreatedBy());
        newsFeedView.setCampName(getCampNameById(news.getCampId()));
        Date newsDate = news.getCreatedDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(newsDate);
        if (newsDate != null) {
            newsFeedView.setCreatedByDate(newsDate.getTime() + "");
        }

        if (news.getMessageType() != null) {
            newsFeedView.setMessageType(news.getMessageType().name());
        }
        return newsFeedView;
    }


    private Date convertDate(String date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            Date newDate = calendar.getTime();
            return newDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Long(date));
        Date newDate = calendar.getTime();
        return newDate;
    }

    private Date convertDateFromCalendar(Calendar date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            Date newDate = calendar.getTime();
            return newDate;
        }
        Date newDate = date.getTime();

        return newDate;
    }

    private void expireDate(NewsFeed newsFeed) {
        if (newsFeed.getCreatedDate() == null) {
            Calendar calendar = Calendar.getInstance();
            Date newexDate = calendar.getTime();
            newsFeed.setExpirationDate(newexDate);
        }

        if (newsFeed.getMessageType() == NewsType.ALERT) {
            Date expireDate = new Date(newsFeed.getCreatedDate().getTime() + 30 * ((1000 * 60 * 60 * 24)));
            newsFeed.setExpirationDate(expireDate);
        } else if (newsFeed.getMessageType() == NewsType.UPDATE) {
            Date expireDate = new Date(newsFeed.getCreatedDate().getTime() + 60 * ((1000 * 60 * 60 * 24)));
            newsFeed.setExpirationDate(expireDate);
        } else {
            Date expireDate = new Date(newsFeed.getCreatedDate().getTime() + 90 * ((1000 * 60 * 60 * 24)));
            newsFeed.setExpirationDate(expireDate);
        }
    }

    private String getCampNameById(Long campId) {
        String campName = null;
        if (campId != null) {
            Camp camp = campService.findById(campId);
            campName = camp.getName();
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

    private CsbUser getUserByEmail(String userEmail) {
        List<CsbUser> userList = adminService.findAll();
        if (userEmail != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getEmail().equalsIgnoreCase(userEmail)) {
                    return csbUser;
                }
            }
        }
        return null;
    }

    public List<Event> getEvents() {
        List<Event> eventList = eventService.findAll();
        if (eventList != null) {
            return eventList;
        }
        return null;
    }

    public Event getEventById(Long id) {

        return eventService.findById(id);

    }

    public List<NewsFeed> getAllComments(Long id) {
        List<NewsFeed> newsList = newsFeedService.findAllComments(id);
        if (newsList != null) {
            return newsList;
        }
        return null;
    }

    public Long getUserIdByEmail(String userEmail) {
        List<CsbUser> userList = adminService.findAll();
        if (userEmail != null) {
            for (CsbUser csbUser : userList) {
                if (csbUser.getEmail().equalsIgnoreCase(userEmail)) {
                    return csbUser.getId();
                }
            }
        }
        return new Long(1);
    }

    public Page<NewsFeedView> buildNewsFeedPage(PageRequest pageRequest) throws Exception {
        System.out.println("BUILDINGNEWSPAGE");
        List<NewsFeedView> listOfNews = new ArrayList<NewsFeedView>();
        Page<Event> pageOfNews = null;
        PageRequest pageRequestResponse = null;
        pageOfNews = this.eventService.findAll(pageRequest);

        System.out.println("SIZE " + pageOfNews.getSize());
        System.out.println( pageOfNews.getContent());
        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfNews.getNumber(), pageOfNews.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfNews.getNumber(), pageOfNews.getSize(), pageOfNews.getSort());
        }

        if (pageOfNews != null) {
            System.out.println(pageOfNews != null);
            for (Event event : pageOfNews.getContent()) {
                System.out.println(event);
                listOfNews.add(transformEventToNewsFeed(event));
            }
        }

        PageImpl page = new PageImpl(listOfNews, pageRequestResponse, pageOfNews.getTotalElements());

        return page;
    }
}
