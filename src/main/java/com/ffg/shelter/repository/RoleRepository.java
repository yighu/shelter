package com.ffg.shelter.repository;

import com.ffg.shelter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select u from Role u where u.roleType = :roleType")
    List<Role> findByRoleName(
            @Param("roleType") String roleType);
}
