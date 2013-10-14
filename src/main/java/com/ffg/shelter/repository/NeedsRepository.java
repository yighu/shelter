package com.ffg.shelter.repository;

import com.ffg.shelter.model.Needs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NeedsRepository extends JpaRepository<Needs, Long> {
}
