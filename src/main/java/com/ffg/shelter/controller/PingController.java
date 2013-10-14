package com.ffg.shelter.controller;

import com.ffg.shelter.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;


@Controller
public class PingController {

    private ClientRepository repository;

    @Inject
    @Named("clientRepository")
    public void setRepository(ClientRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public void ping() {
        this.repository.exists(0L);
    }
}
