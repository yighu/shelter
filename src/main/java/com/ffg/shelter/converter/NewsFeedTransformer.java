package com.ffg.shelter.converter;

import com.ffg.shelter.model.Event;
import com.ffg.shelter.model.NewsFeed;
import com.ffg.shelter.view.NewsFeedView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface NewsFeedTransformer {

    NewsFeed transformToEntityFromNewsFeedView(NewsFeedView newsFeedView);

    NewsFeedView transformToEntityFromNews(NewsFeed newsFeed);

    NewsFeedView transformEventToNewsFeed(Event event);

    Long getUserIdByEmail(String userEmail);

    Event getEventById(Long id);

    List<Event> getEvents();

    List<NewsFeed> getAllComments(Long id);

     Page<NewsFeedView> buildNewsFeedPage(PageRequest pageRequest) throws Exception;
}
