package com.ffg.shelter.repository;

import com.ffg.shelter.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByClientId(Long id);

    @Query("select u from Image u where u.campId = :campId")
    Image findByCampId(@Param("campId") long campId);

}
