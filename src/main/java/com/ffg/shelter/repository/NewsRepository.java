package com.ffg.shelter.repository;

import com.ffg.shelter.model.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NewsRepository extends JpaRepository<NewsFeed, Long> {
    @Query("select u from NewsFeed u where u.id = :id")
    NewsFeed findNewsById(
            @Param("id") Long id);

    @Query("select u from NewsFeed u where u.eventId = :id")
    List<NewsFeed> findCommentsById(
            @Param("id") Long id
    );

    @Query("select u from NewsFeed u where u.expirationDate >= CURRENT_DATE order by u.expirationDate DESC LIMIT 10 ")
    List<NewsFeed> findAllNewsNotExpired();

}
