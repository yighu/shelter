package com.ffg.shelter.repository;

import com.ffg.shelter.model.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PrivilegeTypeRepository extends JpaRepository<PrivilegeType, Long> {
    @Query("select u from PrivilegeType u where u.privilegeType = :privilegeType")
    List<PrivilegeType> findByPrivilegeType(
            @Param("privilegeType") String privilegeType);

}
