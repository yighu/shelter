package com.ffg.shelter.controller;

import com.ffg.shelter.converter.EventTransformer;
import com.ffg.shelter.converter.NewsFeedTransformer;
import com.ffg.shelter.model.Event;
import com.ffg.shelter.model.NewsFeed;
import com.ffg.shelter.service.NewsFeedService;
import com.ffg.shelter.view.NewsFeedView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;



@Controller
@RequestMapping(value = "/api/news")
public class NewsController extends ServiceBasedRestController<NewsFeed, Long, NewsFeedService> {

private static final Logger log=Logger.getLogger(NewsController.class.getName());
    private NewsFeedTransformer newsFeedTransformer;
    private EventTransformer eventTransformer;
    private EventController eventController;

    @Inject
    @Named("newsFeedService")
    @Override
    public void setService(NewsFeedService service) {
        this.service = service;
    }


    @Inject
    @Named("newsFeedTransformer")
    public void setNewsFeedTransformer(NewsFeedTransformer newsFeedTransformer) {
        this.newsFeedTransformer = newsFeedTransformer;
    }

    @RequestMapping(value = "newsDetails")
    @ResponseBody
    public NewsFeedView getNewsDetail(@RequestParam(value = "newsFeedId", required = false) String newsFeedId) throws IOException {
        Long id = Long.parseLong(newsFeedId);
        return newsFeedTransformer.transformToEntityFromNews(this.service.findNewsById(id));
    }

    @RequestMapping(value = "commentsDetails")
    @ResponseBody
    public NewsFeedView getCommentsDetail(@RequestParam(value = "newsFeedId", required = false) String newsFeedId) throws IOException {
        Long id = Long.parseLong(newsFeedId);
        return newsFeedTransformer.transformToEntityFromNews(this.service.findNewsById(id));
    }

    @RequestMapping(value = "createNews")
    @ResponseBody
    public NewsFeedView createNews(@RequestBody NewsFeedView newsFeedDetailsView) throws IOException {
        NewsFeed news = newsFeedTransformer.transformToEntityFromNewsFeedView(newsFeedDetailsView);
        return newsFeedTransformer.transformToEntityFromNews(this.service.create(news));

    }

    @RequestMapping(value = "updateNews")
    @ResponseBody
    public NewsFeedView updateNews(@RequestBody NewsFeedView newsFeedDetails) throws IOException {
        NewsFeed news = newsFeedTransformer.transformToEntityFromNewsFeedView(newsFeedDetails);
        return newsFeedTransformer.transformToEntityFromNews(this.service.create(news));
    }

    @RequestMapping(value = "news")
    @ResponseBody
    public List<NewsFeedView> findAllNews() throws IOException {

        List<NewsFeedView> listNews = new ArrayList<NewsFeedView>();
        for (Event event : newsFeedTransformer.getEvents()) {
            listNews.add(newsFeedTransformer.transformEventToNewsFeed(event));
        }
        return listNews;

    }

    @RequestMapping(value = "page")
    @ResponseBody
    public Page<NewsFeedView> findAllEventsPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                      @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                      @RequestParam(value = "properties", required = false) String properties) throws Exception {

        log.info("BEGIN!!!");
        Assert.isTrue(page > 0, "Page index must be greater than 0");
        log.info("HI!!!");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        log.info("WOAH!!!");
        if (direction.isEmpty()) {
            return newsFeedTransformer.buildNewsFeedPage(new PageRequest(page  - 1, size ));
        } else {
            Assert.notNull(properties);
            return newsFeedTransformer.buildNewsFeedPage(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

    }


    @RequestMapping(value = "newsRetrieve")
    @ResponseBody
    public NewsFeedView findNews(@RequestParam(value = "eventId", required = false) Long id) throws IOException {
        return newsFeedTransformer.transformEventToNewsFeed(newsFeedTransformer.getEventById(id));

    }

    @RequestMapping(value = "comments")
    @ResponseBody
    public List<NewsFeedView> findAllComments(@RequestParam(value = "newsFeedId", required = false) Long id) throws IOException {

        List<NewsFeedView> listComments = new ArrayList<NewsFeedView>();
        for (NewsFeed news : newsFeedTransformer.getAllComments(id)) {
            listComments.add(newsFeedTransformer.transformToEntityFromNews(news));
        }
        return listComments;

    }
}
