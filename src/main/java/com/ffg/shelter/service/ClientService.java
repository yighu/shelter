package com.ffg.shelter.service;

import com.ffg.shelter.model.Client;
import com.ffg.shelter.view.ClientSearchView;
import org.resthub.common.service.CrudService;

import java.util.List;


public interface ClientService extends CrudService<Client, Long> {
    public List<ClientSearchView> findByClientName(String firstName);

    public Client findClientById(Long id);

    public Client findIdByClientName(String name);

}
