package com.ffg.shelter.repository;

import com.ffg.shelter.model.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CampRepository extends JpaRepository<Camp, Long> {
    @Query("select u from Camp u where REPLACE(UPPER(u.name), ' ', '') like :campName")
    List<Camp> findByCampName(
            @Param("campName") String campName);

    @Query("select u from Camp u where u.id = :campId")
    Camp findById(
            @Param("campId") Long campId);

    @Query("select u from Camp u where u.name = :name")
    Camp findIdByCampName(
            @Param("name") String name);
}
