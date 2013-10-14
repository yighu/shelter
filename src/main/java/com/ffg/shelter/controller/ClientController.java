package com.ffg.shelter.controller;

import com.ffg.shelter.converter.ClientTransformer;
import com.ffg.shelter.exception.ValidationErrors;
import com.ffg.shelter.model.Client;
import com.ffg.shelter.service.ClientService;
import com.ffg.shelter.view.ClientDetailView;
import com.ffg.shelter.view.ClientListView;
import com.ffg.shelter.view.ClientSearchView;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/api/client")
public class ClientController extends ServiceBasedRestController<Client, Long, ClientService> {

    private ClientTransformer clientTransformer;

    @Inject
    @Named("clientService")
    @Override
    public void setService(ClientService service) {
        this.service = service;
    }


    @Inject
    @Named("clientTransformer")
    public void setClientTransformer(ClientTransformer clientTransformer) {
        this.clientTransformer = clientTransformer;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public List<ClientSearchView> getClientByName(@RequestParam(value = "firstName", required = false) String firstName) throws IOException {

        if (firstName != null)
            firstName = "%" + firstName.toUpperCase().replaceAll("\\s", "") + "%";
        return this.service.findByClientName(firstName);

    }


    @RequestMapping(value = "details")
    @ResponseBody
    public ClientDetailView getClientDetail(@RequestParam(value = "clientId", required = false) Long clientId) throws IOException {
        return clientTransformer.transformFromEntityToDetailView(this.service.findById(clientId));
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public ClientDetailView createClient(@Valid @RequestBody ClientDetailView clientDetailView, BindingResult result) throws Exception {
        ValidationErrors.check(result);
        Client client = clientTransformer.transformToEntityFromDetailView(clientDetailView);
        return clientTransformer.transformFromEntityToDetailView(this.service.create(client));
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ClientDetailView updateClient(@Valid @RequestBody ClientDetailView clientDetailView, BindingResult result) throws Exception {
        ValidationErrors.check(result);
        return clientTransformer.transformFromEntityToDetailView(this.service.create(clientTransformer.transformToEntityFromDetailView(clientDetailView)));
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public List<ClientListView> findAllClients() throws IOException {
        List<ClientListView> listClients = new ArrayList<ClientListView>();
        for (Client client : this.service.findAll()) {
            listClients.add(clientTransformer.transformFromEntityToListView(client));
        }
        return listClients;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteClient(@RequestParam(value = "clientId", required = false) Long clientId) throws IOException {
        this.service.delete(clientId);
    }

    @RequestMapping(value = "listByPage")
    @ResponseBody
    public Page<ClientListView> findAllUsersPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                      @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                      @RequestParam(value = "properties", required = false) String properties) throws Exception {

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if (direction.isEmpty()) {
            return clientTransformer.buildClientViewPage(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            return clientTransformer.buildClientViewPage(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

    }

    @RequestMapping(value = "queryByPage")
    @ResponseBody
    public Page<ClientListView> findAllUsersPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                      @RequestParam(value = "direction", required = false, defaultValue = "") String direction,
                                                      @RequestParam(value = "properties", required = false) String properties, @RequestParam(value = "firstName", required = false) String firstName) throws Exception {

        Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(direction.isEmpty() || direction.equalsIgnoreCase(Sort.Direction.ASC.toString()) || direction.equalsIgnoreCase(Sort.Direction.DESC.toString()), "Direction should be ASC or DESC");
        if (direction.isEmpty()) {
            return clientTransformer.buildClientViewPage(new PageRequest(page - 1, size));
        } else {
            Assert.notNull(properties);
            return clientTransformer.buildClientViewPage(new PageRequest(page - 1, size, new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(","))));
        }

    }

}
