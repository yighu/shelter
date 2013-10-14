package com.ffg.shelter.service;


import com.ffg.shelter.model.NewsFeed;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface NewsFeedService extends CrudService<NewsFeed, Long> {
    public NewsFeed findNewsById(Long id);


    public List<NewsFeed> findAllComments(Long id);
}
