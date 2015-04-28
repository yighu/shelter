package com.ffg.shelter.view;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/1/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientDetailView {
    private Long id;
    @NotEmpty(message = "FirstName Required")
    @Size(min = 1, max = 30, message = "Client First Name Length Error")
    private String firstName;

    @NotEmpty(message = "LastName Required ")
    @Size(min = 1, max = 30, message = "Client Last Name length error")
    private String lastName;

    @Size(min = 1, max = 30, message = "Client alias lenght error")
    private String alias;

    @NotEmpty(message = "Please select gender")
    private String gender;
    private String priority;
    private String status;
    private String attitude;
    @NotEmpty(message = "Date of birth is required")
    @Pattern(regexp = "^(0[1-9]|1[012])\\/([0-2][1-9]|[12][0]|3[0-2])\\/((19|20)\\d\\d)$", message = "invalid dateOfBirth date format")
    private String dateOfBirth;
    private String dateOfBirthYear;
    private String dateOfBirthMonth;
    private String dateOfBirthDay;
    private String age;
    private String pregnant;
    private List<ClientCampView> camp;
    @NotEmpty
    private String caseMgr;
    private String numPets;
    private String numOfChildren;
    private String violentStatus;
    private String substanceAbuseStatus;
    private String healthStatus;
    private String mentalStatus;
    private String attitudeTowardHousing;
    private String housingStage;
    @Size(min = 0, max = 255)
    private String barriers;
    @Pattern(regexp = "^([2-9][0-9]{2})-([2-9][0-9]{2})-([0-9]{4})$", message = " primaryPhone number format is XXX-XXX-XXXX")
    private String primaryPhone;
    @Pattern(regexp = "^([2-9][0-9]{2})-([2-9][0-9]{2})-([0-9]{4})$", message = " secondaryPhone number format is XXX-XXX-XXXX")
    private String secondaryPhone;
    @Size(min = 0, max = 30)
    private String emergencyContactName;
    @Pattern(regexp = "^([2-9][0-9]{2})-([2-9][0-9]{2})-([0-9]{4})$", message = " emergencyContactPhone number format is XXX-XXX-XXXX")
    private String emergencyContactPhone;
    private String caseNotes;
    private String documents;
    @NotEmpty(message = "Last Contact Date is required")
    @Pattern(regexp = "^(0[1-9]|1[012])\\/([0-2][1-9]|[12][0]|3[0-2])\\/((19|20)\\d\\d)$", message = " lastContactDate invalid  date format")
    private String lastContactDate;
    private String lastContactMonth;
    private String lastContactDay;
    private String lastContactYear;
    private String lastContact;
    private String primaryCamp;
    private String secondaryCamp;
    @Size(min = 1, max = 1000, message = "violent Description length error")
    private String violentDescription;
    @Size(min = 1, max = 1000, message = "substance Description length error")
    private String substanceDescription;
    @Size(min = 1, max = 1000, message = "health Description length error")
    private String healthStatusDescription;
    @Size(min = 1, max = 1000, message = "mental Description length error")
    private String mentalStatusDescription;
    private boolean transportation = false;
    private boolean healthCare = false;
    private boolean food = false;
    private boolean clothing = false;
    private boolean medicalSupplies = false;
    private boolean personalHygiene = false;
    private boolean linkageServices = false;
    private String imageROI;
    private String contactROI;

    public String getContactROI() {
        return contactROI;
    }

    public void setContactROI(String contactROI) {
        this.contactROI = contactROI;
    }

    public String getImageROI() {
        return imageROI;
    }

    public void setImageROI(String imageROI) {
        this.imageROI = imageROI;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(String lastContactDate) {
        this.lastContactDate = lastContactDate;
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

    public String getPrimaryCamp() {
        return primaryCamp;
    }

    public void setPrimaryCamp(String primaryCamp) {
        this.primaryCamp = primaryCamp;
    }

    public String getSecondaryCamp() {
        return secondaryCamp;
    }

    public void setSecondaryCamp(String secondaryCamp) {
        this.secondaryCamp = secondaryCamp;
    }

    public String getLastContact() {
        return lastContact;
    }

    public void setLastContact(String lastContact) {
        this.lastContact = lastContact;
    }

    private List<CampCaseMangerView> listOfCaseMgr;

    public String getDateOfBirthYear() {
        return dateOfBirthYear;
    }

    public void setDateOfBirthYear(String dateOfBirthYear) {
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public String getDateOfBirthMonth() {
        return dateOfBirthMonth;
    }

    public void setDateOfBirthMonth(String dateOfBirthMonth) {
        this.dateOfBirthMonth = dateOfBirthMonth;
    }

    public String getDateOfBirthDay() {
        return dateOfBirthDay;
    }

    public void setDateOfBirthDay(String dateOfBirthDay) {
        this.dateOfBirthDay = dateOfBirthDay;
    }

    public String getLastContactMonth() {
        return lastContactMonth;
    }

    public void setLastContactMonth(String lastContactMonth) {
        this.lastContactMonth = lastContactMonth;
    }

    public String getLastContactDay() {
        return lastContactDay;
    }

    public void setLastContactDay(String lastContactDay) {
        this.lastContactDay = lastContactDay;
    }

    public String getLastContactYear() {
        return lastContactYear;
    }

    public void setLastContactYear(String lastContactYear) {
        this.lastContactYear = lastContactYear;
    }

    public List<CampCaseMangerView> getListOfCaseMgr() {
        return listOfCaseMgr;
    }

    public void setListOfCaseMgr(List<CampCaseMangerView> listOfCaseMgr) {
        this.listOfCaseMgr = listOfCaseMgr;
    }

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPregnant() {
        return pregnant;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }


    public String getCaseMgr() {
        return caseMgr;
    }

    public void setCaseMgr(String caseMgr) {
        this.caseMgr = caseMgr;
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


    public String getViolentStatus() {
        return violentStatus;
    }

    public void setViolentStatus(String violentStatus) {
        this.violentStatus = violentStatus;
    }

    public String getSubstanceAbuseStatus() {
        return substanceAbuseStatus;
    }

    public void setSubstanceAbuseStatus(String substanceAbuseStatus) {
        this.substanceAbuseStatus = substanceAbuseStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getMentalStatus() {
        return mentalStatus;
    }

    public void setMentalStatus(String mentalStatus) {
        this.mentalStatus = mentalStatus;
    }

    public String getAttitudeTowardHousing() {
        return attitudeTowardHousing;
    }

    public void setAttitudeTowardHousing(String attitudeTowardHousing) {
        this.attitudeTowardHousing = attitudeTowardHousing;
    }

    public String getHousingStage() {
        return housingStage;
    }

    public void setHousingStage(String housingStage) {
        this.housingStage = housingStage;
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

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
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

    public List<ClientCampView> getCamp() {
        return camp;
    }

    public void setCamp(List<ClientCampView> camp) {
        this.camp = camp;
    }

    public boolean isTransportation() {
        return transportation;
    }

    public void setTransportation(boolean transportation) {
        this.transportation = transportation;
    }

    public boolean isHealthCare() {
        return healthCare;
    }

    public void setHealthCare(boolean healthCare) {
        this.healthCare = healthCare;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isClothing() {
        return clothing;
    }

    public void setClothing(boolean clothing) {
        this.clothing = clothing;
    }

    public boolean isMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(boolean medicalSupplies) {
        this.medicalSupplies = medicalSupplies;
    }

    public boolean isPersonalHygiene() {
        return personalHygiene;
    }

    public void setPersonalHygiene(boolean personalHygiene) {
        this.personalHygiene = personalHygiene;
    }

    public boolean isLinkageServices() {
        return linkageServices;
    }

    public void setLinkageServices(boolean linkageServices) {
        this.linkageServices = linkageServices;
    }


}
