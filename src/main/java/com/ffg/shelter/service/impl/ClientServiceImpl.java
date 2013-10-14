package com.ffg.shelter.service.impl;

import com.ffg.shelter.converter.ClientTransformer;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.repository.ClientRepository;
import com.ffg.shelter.service.ClientService;
import com.ffg.shelter.view.ClientSearchView;
import org.resthub.common.service.CrudServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Named("clientService")
public class ClientServiceImpl extends CrudServiceImpl<Client, Long, ClientRepository> implements ClientService {

    private ClientTransformer clientTransformer;

    @Override
    @Inject
    public void setRepository(ClientRepository clientRepository) {
        super.setRepository(clientRepository);
    }


    @Inject
    @Named("clientTransformer")
    public void setClientTransformer(ClientTransformer clientTransformer) {
        this.clientTransformer = clientTransformer;
    }

    public List<ClientSearchView> findByClientName(String firstName) {

        List<Client> clients = this.repository.findByLastnameOrFirstname(firstName);
        List<ClientSearchView> clientSearchViewList = new ArrayList<ClientSearchView>();
        for (Client client : clients) {
            clientSearchViewList.add(clientTransformer.transformFromEntityToSearchView(client));
        }
        return clientSearchViewList;

    }

    public Client findClientById(Long id) {
        Client client = this.repository.findClientById(id);
        return client;
    }

    public Client findIdByClientName(String name) {
        Client client = this.repository.findClientByName(name);
        return client;
    }

}
