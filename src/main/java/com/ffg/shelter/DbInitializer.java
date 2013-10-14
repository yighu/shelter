package com.ffg.shelter;

import com.ffg.shelter.repository.CampRepository;
import com.ffg.shelter.repository.ClientRepository;
import com.ffg.shelter.repository.EventRepository;
import com.ffg.shelter.repository.NewsRepository;
import org.resthub.common.util.PostInitialize;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("dbInitializer")
public class DbInitializer {

    @Inject
    @Named("clientRepository")
    private ClientRepository clientRepository;

    @Inject
    @Named("campRepository")
    private CampRepository campRepository;

    @Inject
    @Named("eventRepository")
    private EventRepository eventRepository;

    @Inject
    @Named("newsRepository")
    private NewsRepository newsFeedRepository;

    @PostInitialize
    @Transactional(readOnly = false)
    public void init() throws IOException {
        /*
       ObjectMapper mapper = new ObjectMapper();

       File clientFile = new ClassPathResource("csb_clients.json").getFile();
       List<Client> clients = mapper.readValue(clientFile, new TypeReference<List<Client>>() {
       });
       File campFile = new ClassPathResource("csb_camp.json").getFile();
       List<Camp> camps = mapper.readValue(campFile, new TypeReference<List<Camp>>() {
       });
       File eventFile = new ClassPathResource("csb_event.json").getFile();
       List<Event> events = mapper.readValue(eventFile, new TypeReference<List<Event>>() {
       });

       for (Camp camp : camps) {
           //       campRepository.save(camp);
       }

       for (Event event : events) {
           eventRepository.save(event);
       }

       for (Client client : clients) {
           client.setLastContactDate(Calendar.getInstance());
           clientRepository.save(client);

       }

        */
    }
}

