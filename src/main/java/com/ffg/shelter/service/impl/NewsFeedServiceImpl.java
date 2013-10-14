package com.ffg.shelter.service.impl;

import com.ffg.shelter.converter.NewsFeedTransformer;
import com.ffg.shelter.model.NewsFeed;
import com.ffg.shelter.repository.NewsRepository;
import com.ffg.shelter.service.NewsFeedService;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Transactional
@Named("newsFeedService")
public class NewsFeedServiceImpl extends CrudServiceImpl<NewsFeed, Long, NewsRepository> implements NewsFeedService {

    private NewsFeedTransformer newsFeedTransformer;

    @Override
    @Inject
    public void setRepository(NewsRepository newsFeedRepository) {
        super.setRepository(newsFeedRepository);
    }

    @Inject
    @Named("newsFeedTransformer")
    public void setNewsFeedTransformer(NewsFeedTransformer newsFeedTransformer) {
        this.newsFeedTransformer = newsFeedTransformer;
    }

    public NewsFeed findNewsById(Long id) {

        NewsFeed news = this.repository.findNewsById(id);
        return news;
    }

    public List<NewsFeed> findAllComments(Long id) {
        List<NewsFeed> news = this.repository.findCommentsById(id);
        return news;
    }


}
