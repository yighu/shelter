package com.ffg.shelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Client implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String alias;
    private Gender gender;
    private ClientPriority clientPriority;
    private ClientStatus status;
    private Attitude attitude;
    private Calendar dateOfBirth;
    private Pregnant pregnant;
    protected Set<Camp> camps = new HashSet<Camp>();
    private String caseMgr;
    private Violent violentStatus;
    private SubstanceAbuse substanceAbuseStatus;
    private HealthStatus healthStatus;
    private MentalStatus mentalStatus;
    private HouseAttitude attitudeTowardHousing;
    private HousingStage housingStage;
    private String barriers;
    private String primaryPhone;
    private String secondaryPhone;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String caseNotes;
    private String documents;
    private Calendar lastContactDate;
    private String violentDescription;
    private String substanceDescription;
    private String healthStatusDescription;
    private String mentalStatusDescription;
    private String numPets;
    private String numOfChildren;
    protected Set<Needs> needsSet = new HashSet<Needs>();
    private Acquired imageROI;
    private Acquired contactROI;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "needs_id")
    )
    public Set<Needs> getNeedsSet() {
        return needsSet;
    }

    public void setNeedsSet(Set<Needs> needsSet) {
        this.needsSet = needsSet;
    }

    public String getViolentDescription() {
        return violentDescription;
    }

    public void setViolentDescription(String violentDescription) {
        this.violentDescription = violentDescription;
    }

    public String getSubstanceDescription() {
        return substanceDescription;
    }

    public void setSubstanceDescription(String substanceDescription) {
        this.substanceDescription = substanceDescription;
    }

    public String getHealthStatusDescription() {
        return healthStatusDescription;
    }

    public void setHealthStatusDescription(String healthStatusDescription) {
        this.healthStatusDescription = healthStatusDescription;
    }

    public String getMentalStatusDescription() {
        return mentalStatusDescription;
    }

    public void setMentalStatusDescription(String mentalStatusDescription) {
        this.mentalStatusDescription = mentalStatusDescription;
    }

    @Enumerated(EnumType.STRING)
    public Attitude getAttitude() {
        return attitude;
    }

    public void setAttitude(Attitude attitude) {
        this.attitude = attitude;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getCaseMgr() {
        return caseMgr;
    }

    public void setCaseMgr(String caseMgr) {
        this.caseMgr = caseMgr;
    }

    @Enumerated(EnumType.STRING)
    public Violent getViolentStatus() {
        return violentStatus;
    }

    public void setViolentStatus(Violent violentStatus) {
        this.violentStatus = violentStatus;
    }

    @Enumerated(EnumType.STRING)
    public SubstanceAbuse getSubstanceAbuseStatus() {
        return substanceAbuseStatus;
    }

    public void setSubstanceAbuseStatus(SubstanceAbuse substanceAbuseStatus) {
        this.substanceAbuseStatus = substanceAbuseStatus;
    }

    @Enumerated(EnumType.STRING)
    public HouseAttitude getAttitudeTowardHousing() {
        return attitudeTowardHousing;
    }

    public void setAttitudeTowardHousing(HouseAttitude attitudeTowardHousing) {
        this.attitudeTowardHousing = attitudeTowardHousing;
    }

    public String getBarriers() {
        return barriers;
    }

    public void setBarriers(String barriers) {
        this.barriers = barriers;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getCaseNotes() {
        return caseNotes;
    }

    public void setCaseNotes(String caseNotes) {
        this.caseNotes = caseNotes;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public Calendar getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(Calendar lastContactDate) {
        this.lastContactDate = lastContactDate;
    }


    /*
   @ManyToMany
   @JoinTable(
           joinColumns =
           @JoinColumn(name = "id", referencedColumnName = "id"),
           inverseJoinColumns =
           @JoinColumn(name = "child_id", referencedColumnName = "id")
   )
   public Set<Child> getChildren() {
       return children;
   }

   public void setChildren(Set<Child> children) {
       this.children = children;
   }
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Enumerated(EnumType.STRING)
    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    public HousingStage getHousingStage() {
        return housingStage;
    }

    public void setHousingStage(HousingStage housingStage) {
        this.housingStage = housingStage;
    }


    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    @Enumerated(EnumType.STRING)
    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Enumerated(EnumType.STRING)
    public MentalStatus getMentalStatus() {
        return mentalStatus;
    }

    public void setMentalStatus(MentalStatus mentalStatus) {
        this.mentalStatus = mentalStatus;
    }


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "camp_id")
    )
    public Set<Camp> getCamps() {
        return camps;
    }

    public void setCamps(Set<Camp> camps) {
        this.camps = camps;
    }

    public void setClientPriority(ClientPriority clientPriority) {
        this.clientPriority = clientPriority;
    }

    @Enumerated(EnumType.STRING)
    public ClientPriority getClientPriority() {
        return this.clientPriority;
    }

    public void setPregnant(Pregnant pregnant) {
        this.pregnant = pregnant;
    }

    @Enumerated(EnumType.STRING)
    public Pregnant getPregnant() {
        return this.pregnant;
    }

    public String getNumPets() {
        return numPets;
    }

    public void setNumPets(String numPets) {
        this.numPets = numPets;
    }

    public String getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(String numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    @Enumerated(EnumType.STRING)
    public Acquired getImageROI() {
        return imageROI;
    }

    public void setImageROI(Acquired imageROI) {
        this.imageROI = imageROI;
    }

    @Enumerated(EnumType.STRING)
    public Acquired getContactROI() {
        return contactROI;
    }

    public void setContactROI(Acquired contactROI) {
        this.contactROI = contactROI;
    }
}