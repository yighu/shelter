package com.ffg.shelter.repository;

import com.ffg.shelter.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Query("select u from Feature u where u.featureName = :featureName")
    List<Feature> findAllFeatureByFeatureName(@Param("featureName") String featureName);

    @Query("select u from Feature u where u.workFlowName = :workFlow_Name")
    List<Feature> findAllFeatureByWorkFlow(@Param("workFlow_Name") String workFlowName);


}
