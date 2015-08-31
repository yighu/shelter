package com.ffg.shelter.converter;

import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.model.*;
import com.ffg.shelter.repository.NeedsRepository;
import com.ffg.shelter.service.AdminService;
import com.ffg.shelter.service.CampService;
import com.ffg.shelter.service.ClientService;
import com.ffg.shelter.service.ImageService;
import com.ffg.shelter.view.*;
import com.google.appengine.repackaged.org.joda.time.DateMidnight;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.google.appengine.repackaged.org.joda.time.Years;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.logging.Logger;


@Named("clientTransformer")
public class ClientTransformerImpl implements ClientTransformer {

    private ImageService imageService;
private static final Logger log=Logger.getLogger(ClientTransformerImpl.class.getName());


    @Inject
    @Named("imageService")
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    private ClientService clientService;


    @Inject
    @Named("clientService")
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    private CampService service;


    @Inject
    @Named("campService")
    public void setService(CampService service) {
        this.service = service;
    }

    private AdminService adminService;

    @Inject
    @Named("adminService")
    public void setService(AdminService adminService) {
        this.adminService = adminService;
    }

    private NeedsRepository needsRepository;

    @Inject
    @Named("needsRepository")
    public void setNeedsRepository(NeedsRepository needsRepository) {
        this.needsRepository = needsRepository;
    }

    public ClientSearchView transformFromEntityToSearchView(Client client) {
        ClientSearchView clientSearchView = new ClientSearchView();
        clientSearchView.setAge(calculateAge(client.getDateOfBirth()));
        clientSearchView.setFirstName(client.getFirstName());
        if (client.getGender() != null) {
            clientSearchView.setGender(client.getGender().name());
        }
        clientSearchView.setId(client.getId());
        clientSearchView.setLastName(client.getLastName());
        return clientSearchView;
    }

    public ClientListView transformFromEntityToListView(Client client) {
        ClientListView clientListView = new ClientListView();
        clientListView.setAge(calculateAge(client.getDateOfBirth()));
        if (client.getAttitude() != null) {
            clientListView.setAttitude(client.getAttitude().name());
        }

        if (getCampsForClient(client.getCamps()).size() > 0) {
            clientListView.setCamp(getCampsForClient(client.getCamps()).get(0));
        }
        clientListView.setId(client.getId());
        if (client.getLastContactDate() != null) {
            Calendar calendar = client.getLastContactDate();
            if (calendar != null) {
                clientListView.setLastContact(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
            }
        }
        clientListView.setName(client.getFirstName() + " " + client.getLastName());
        clientListView.setNumOfChildren(client.getNumOfChildren());
        if (client.getStatus() != null) {
            clientListView.setStatus(client.getStatus().name());
        }
        return clientListView;
    }

    public ClientDetailView transformFromEntityToDetailView(Client client) {
        ClientDetailView clientDetailView = new ClientDetailView();
        if (client.getStatus() != null) {
            clientDetailView.setStatus(client.getStatus().name());
        }
        clientDetailView.setAge(calculateAge(client.getDateOfBirth()));
        clientDetailView.setAlias(client.getAlias());
        if (client.getAttitude() != null) {
            clientDetailView.setAttitude(client.getAttitude().name());
        }
        if (client.getAttitudeTowardHousing() != null) {
            clientDetailView.setAttitudeTowardHousing(client.getAttitudeTowardHousing().name());
        }
        clientDetailView.setBarriers(client.getBarriers());
        clientDetailView.setCaseMgr(client.getCaseMgr());
        clientDetailView.setCaseNotes(client.getCaseNotes());
        if (client.getDateOfBirth() != null) {
            clientDetailView.setDateOfBirthDay(client.getDateOfBirth().get(Calendar.DAY_OF_MONTH) + "");
        }

        if (client.getLastContactDate() != null) {
            Calendar calendar = client.getLastContactDate();
            if (calendar != null) {
                String strdate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                strdate = sdf.format(calendar.getTime());
                clientDetailView.setLastContactDate(strdate);
            }
        }

        if (client.getDateOfBirth() != null) {
            Calendar calendar = client.getDateOfBirth();
            if (calendar != null) {
                String strdate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                strdate = sdf.format(calendar.getTime());
                clientDetailView.setDateOfBirth(strdate);
            }
        }
        clientDetailView.setDocuments(client.getDocuments());
        clientDetailView.setEmergencyContactName(client.getEmergencyContactName());
        clientDetailView.setEmergencyContactPhone(client.getEmergencyContactPhone());
        clientDetailView.setFirstName(client.getFirstName());
        if (client.getGender() != null) {
            clientDetailView.setGender(client.getGender().name());
        }
        if (client.getHealthStatus() != null) {
            clientDetailView.setHealthStatus(client.getHealthStatus().name());
        }
        if (client.getHousingStage() != null) {
            clientDetailView.setHousingStage(client.getHousingStage().name());
        }
        clientDetailView.setId(client.getId());
        clientDetailView.setLastName(client.getLastName());
        if (client.getMentalStatus() != null) {
            clientDetailView.setMentalStatus(client.getMentalStatus().toString());
        }
        if (client.getDateOfBirth() != null) {
            clientDetailView.setDateOfBirthMonth(client.getDateOfBirth().get(Calendar.MONTH) + "");
        }


        Set<Needs> needList = client.getNeedsSet();
        for (Needs needs : needList) {
            if (needs.getNeed() == Need.Transportation) {
                clientDetailView.setTransportation(true);
            }

            if (needs.getNeed() == Need.HealthCare) {
                clientDetailView.setHealthCare(true);
            }

            if (needs.getNeed() == Need.Food) {
                clientDetailView.setFood(true);
            }

            if (needs.getNeed() == Need.Clothing) {
                clientDetailView.setClothing(true);
            }

            if (needs.getNeed() == Need.MedicalSupplies) {
                clientDetailView.setMedicalSupplies(true);
            }

            if (needs.getNeed() == Need.PersonalHygiene) {
                clientDetailView.setPersonalHygiene(true);
            }

            if (needs.getNeed() == Need.LinkageToServices) {
                clientDetailView.setLinkageServices(true);
            }
        }


        clientDetailView.setNumOfChildren(client.getNumOfChildren());
        if (client.getPregnant() != null) {
            clientDetailView.setPregnant(client.getPregnant().toString());
        }
        clientDetailView.setCamp(getCampsForClient(client.getCamps()));
        List<ClientCampView> clientCampViews = getCampsForClient(client.getCamps());
        for (int i = 0; i < clientCampViews.size(); i++) {
            if (i == 0) {
                clientDetailView.setPrimaryCamp(clientCampViews.get(i).getCampName());
            }

            if (i > 0) {
                clientDetailView.setSecondaryCamp(clientCampViews.get(i).getCampName());
            }
        }
        clientDetailView.setPrimaryPhone(client.getPrimaryPhone());
        clientDetailView.setNumPets(client.getNumPets());
        clientDetailView.setSecondaryPhone(client.getSecondaryPhone());
        if (client.getSubstanceAbuseStatus() != null) {
            clientDetailView.setSubstanceAbuseStatus(client.getSubstanceAbuseStatus().name());
        }
        if (client.getDateOfBirth() != null) {
            clientDetailView.setDateOfBirthYear(client.getDateOfBirth().get(Calendar.YEAR) + "");
        }
        if (client.getViolentStatus() != null) {
            clientDetailView.setViolentStatus(client.getViolentStatus().toString());
        }
        if (client.getPregnant() != null) {
            clientDetailView.setPregnant(client.getPregnant().toString());
        }
        if (client.getClientPriority() != null) {
            clientDetailView.setPriority(client.getClientPriority().name());
        }

        if (client.getLastContactDate() != null) {
            Calendar calendar = client.getLastContactDate();
            clientDetailView.setLastContactDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
            clientDetailView.setLastContactMonth(calendar.get(Calendar.MONTH) + "");
            clientDetailView.setLastContactYear(calendar.get(Calendar.YEAR) + "");
        }

        List<CsbUser> userList = adminService.findAll();
        List<CampCaseMangerView> listOfCaseMgr = new ArrayList<CampCaseMangerView>();
        for (CsbUser csbUser : userList) {
            if ((csbUser.getRole() == UserRole.CaseManager) || (csbUser.getRole() == UserRole.Admin)) {
                if (csbUser.getExpiryDate().after(Calendar.getInstance())) {
                    CampCaseMangerView campCaseMangerView = new CampCaseMangerView();
                    campCaseMangerView.setName(csbUser.getFirstName() + " " + csbUser.getLastName());
                    listOfCaseMgr.add(campCaseMangerView);
                }
            }
        }
        clientDetailView.setListOfCaseMgr(listOfCaseMgr);
        clientDetailView.setViolentDescription(client.getViolentDescription());
        clientDetailView.setHealthStatusDescription(client.getHealthStatusDescription());
        clientDetailView.setSubstanceDescription(client.getSubstanceDescription());
        clientDetailView.setMentalStatusDescription(client.getMentalStatusDescription());
        if (client.getImageROI() != null) {
            clientDetailView.setImageROI(client.getImageROI().toString());
        } else {
            clientDetailView.setImageROI(Acquired.NotAcquired.toString());
        }
        if (client.getContactROI() != null) {
            clientDetailView.setContactROI(client.getContactROI().toString());
        } else {
            clientDetailView.setContactROI(Acquired.NotAcquired.toString());
        }
        return clientDetailView;
    }


    public Client transformToEntityFromListView(ClientListView clientListView) {
        return null;
    }

    public Client transformToEntityFromDetailView(ClientDetailView clientDetailView) throws ParseException, BusinessException {
        Client client = new Client();
        client.setStatus(ClientConverter.convertClientStatus(clientDetailView.getStatus()));
        client.setDateOfBirth(convertDate(clientDetailView.getDateOfBirth()));
        client.setAlias(clientDetailView.getAlias());
        client.setAttitude(ClientConverter.convertAttitude(clientDetailView.getAttitude()));
        client.setAttitudeTowardHousing(ClientConverter.convertHouseAttitude(clientDetailView.getAttitudeTowardHousing()));
        client.setBarriers(clientDetailView.getBarriers());
        client.setCaseMgr(clientDetailView.getCaseMgr());
        client.setCaseNotes(clientDetailView.getCaseNotes());
        client.setDocuments(clientDetailView.getDocuments());
        client.setEmergencyContactName(clientDetailView.getEmergencyContactName());
        client.setEmergencyContactPhone(clientDetailView.getEmergencyContactPhone());
        client.setFirstName(clientDetailView.getFirstName());
        client.setGender(ClientConverter.convertGender(clientDetailView.getGender()));
        client.setHealthStatus(ClientConverter.convertHealthStatus(clientDetailView.getHealthStatus()));
        client.setHousingStage(ClientConverter.convertHousingStage(clientDetailView.getHousingStage()));

        if (clientDetailView.getId() != null) {
            client.setId(clientDetailView.getId());
        }
        client.setLastName(clientDetailView.getLastName());
        client.setMentalStatus(ClientConverter.convertMentalStatus(clientDetailView.getMentalStatus()));
        client.setNumOfChildren(clientDetailView.getNumOfChildren());
        client.setPregnant(ClientConverter.convertPregnant(clientDetailView.getPregnant()));
        Set<Camp> bindCamps = new HashSet<Camp>();
        if (clientDetailView.getPrimaryCamp() != null) {
            Camp camp = service.findIdByCampName(clientDetailView.getPrimaryCamp());
            if (camp != null) {
                bindCamps.add(camp);
            }
        }

        if (clientDetailView.getSecondaryCamp() != null) {
            Camp camp = service.findIdByCampName(clientDetailView.getSecondaryCamp());
            if (camp != null) {
                bindCamps.add(camp);
            }

        }
        client.setCamps(bindCamps);
        client.setPrimaryPhone(clientDetailView.getPrimaryPhone());
        client.setNumPets(clientDetailView.getNumPets());
        client.setSecondaryPhone(clientDetailView.getSecondaryPhone());
        client.setSubstanceAbuseStatus(ClientConverter.convertSubstanceAbuse(clientDetailView.getSubstanceAbuseStatus()));
        client.setViolentStatus(ClientConverter.convertViolent(clientDetailView.getViolentStatus()));
        client.setPregnant(ClientConverter.convertPregnant(clientDetailView.getPregnant()));
        client.setClientPriority(ClientConverter.convertClientPriority(clientDetailView.getPriority()));
        client.setLastContactDate(convertDate(clientDetailView.getLastContactDate()));
        client.setViolentDescription(clientDetailView.getViolentDescription());
        client.setHealthStatusDescription(clientDetailView.getHealthStatusDescription());
        client.setSubstanceDescription(clientDetailView.getSubstanceDescription());
        client.setMentalStatusDescription(clientDetailView.getMentalStatusDescription());
        businessValidation(client);
        Set<Needs> needList = client.getNeedsSet();
        if (clientDetailView.isTransportation()) {
            Needs needs = new Needs();
            needs.setNeed(Need.Transportation);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isHealthCare()) {
            Needs needs = new Needs();
            needs.setNeed(Need.HealthCare);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isFood()) {
            Needs needs = new Needs();
            needs.setNeed(Need.Food);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isClothing()) {
            Needs needs = new Needs();
            needs.setNeed(Need.Clothing);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isMedicalSupplies()) {
            Needs needs = new Needs();
            needs.setNeed(Need.MedicalSupplies);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isPersonalHygiene()) {
            Needs needs = new Needs();
            needs.setNeed(Need.PersonalHygiene);
            needList.add(needsRepository.save(needs));
        }

        if (clientDetailView.isLinkageServices()) {
            Needs needs = new Needs();
            needs.setNeed(Need.LinkageToServices);
            needList.add(needsRepository.save(needs));
        }

        service.updateCampOccupantCount(clientDetailView, client);
        client.setContactROI(ClientConverter.convertAcquired(clientDetailView.getContactROI()));
        client.setImageROI(ClientConverter.convertAcquired(clientDetailView.getImageROI()));
        return client;
    }


    private String calculateAge(Calendar dateOfBirth) {
        if (dateOfBirth == null) {
            return "0";
        }
        int month = dateOfBirth.get(Calendar.MONTH);
        if (month == 0) {
            month = 1;
        }
        Years age = null;
        try {
            DateMidnight birthDate = new DateMidnight(dateOfBirth.get(Calendar.YEAR), month, dateOfBirth.get(Calendar.DAY_OF_MONTH));
            DateTime now = new DateTime();
            age = Years.yearsBetween(birthDate, now);
        } catch (Exception e) {
            log.info("Exception" + e.getMessage() + dateOfBirth);
        }
        return age != null ? age.getYears() + "" : "0";
    }

    private List<ClientCampView> getCampsForClient(Set<Camp> camps) {
        ArrayList<ClientCampView> listClientCamp = new ArrayList<ClientCampView>();
        for (Camp camp : camps) {
            if (camp.getStatus() == CampStatus.Active) {
                ClientCampView clientCampView = new ClientCampView();
                clientCampView.setCampName(camp.getName());
                clientCampView.setId(camp.getId());
                listClientCamp.add(clientCampView);
            }
        }
        return listClientCamp;
    }


    private Calendar convertDate(String date) throws BusinessException {
        Calendar eDate = null;
        try {
            eDate = Calendar.getInstance();
            if (date != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                eDate.setTime(formatter.parse(date));
            }
        } catch (Exception e) {
            throw new BusinessException(" Invalid Date ");
        }
        return eDate;

    }

    private void businessValidation(Client client) throws BusinessException {

        if (client.getDateOfBirth().after(Calendar.getInstance())) {
            throw new BusinessException(" Invalid Date ");
        }

        if (client.getGender() == Gender.Male) {

            if (client.getPregnant() != Pregnant.NA) {
                throw new BusinessException(" Male can't be Pregnant ");
            }
        }

        if (client.getLastContactDate().after(Calendar.getInstance())) {
            throw new BusinessException(" Invalid Date ");
        }

        if (client.getContactROI() == Acquired.NotAcquired) {
            if (client.getEmergencyContactName() != null) {
                throw new BusinessException(" Emergency Contact not valid without ROI");
            }
        }

        if (client.getImageROI() == Acquired.NotAcquired) {
            if (imageService.findByCampId(client.getId()) != null) {
                throw new BusinessException(" Client Image is not valid without ROI");
            }
        }

    }


    public Page<ClientListView> buildClientViewPage(PageRequest pageRequest) throws Exception {

        List<ClientListView> listOfClients = new ArrayList<ClientListView>();
        Page<Client> pageOfClients = null;
        PageRequest pageRequestResponse = null;
        pageOfClients = this.clientService.findAll(pageRequest);
        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfClients.getNumber(), pageOfClients.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfClients.getNumber(), pageOfClients.getSize(), pageOfClients.getSort());
        }

        if (pageOfClients != null) {
            for (Client client : pageOfClients.getContent()) {
                listOfClients.add(transformFromEntityToListView(client));
            }
        }

        PageImpl page = new PageImpl(listOfClients, pageRequestResponse, pageOfClients.getTotalElements());

        return page;
    }

    //TODO  have to implement page with search, this is just a template.
    public Page<ClientSearchView> buildCampsViewClientByNamePaginated(PageRequest pageRequest, String firstName) throws Exception {

        List<ClientSearchView> listOfClients = new ArrayList<ClientSearchView>();
        Page<Client> pageOfClients = null;
        PageRequest pageRequestResponse = null;

        if (firstName != null) {
            firstName = "%" + firstName.toUpperCase().replaceAll("\\s", "") + "%";
        }


        if (pageRequest.getSort() == null) {
            pageRequestResponse = new PageRequest(pageOfClients.getNumber(), pageOfClients.getSize());

        } else {
            pageRequestResponse = new PageRequest(pageOfClients.getNumber(), pageOfClients.getSize(), pageOfClients.getSort());
        }

        if (pageOfClients != null) {
            for (Client client : pageOfClients.getContent()) {
                listOfClients.addAll(this.clientService.findByClientName(firstName));
            }
        }

        PageImpl page = new PageImpl(listOfClients, pageRequestResponse, pageOfClients.getTotalElements());

        return page;
    }

}
