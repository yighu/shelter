package com.ffg.shelter.repository;

import com.ffg.shelter.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select u from Client u where CONCAT(UPPER(u.firstName), UPPER(u.lastName)) like :firstname")
    List<Client> findByLastnameOrFirstname(@Param("firstname") String firstname);

    @Query("select u from Client u where u.id like :id")
    Client findClientById(@Param("id") Long id);

    @Query("select u from Client u where CONCAT(UPPER(u.firstName), UPPER(u.lastName)) like :name")
    Client findClientByName(@Param("name") String name);
}
