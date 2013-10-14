package com.ffg.shelter.repository;

import com.ffg.shelter.model.CsbUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<CsbUser, Long> {
}
