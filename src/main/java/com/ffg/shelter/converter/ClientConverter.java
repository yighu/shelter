package com.ffg.shelter.converter;

import com.ffg.shelter.model.*;


public class ClientConverter {


    public static Gender convertGender(String source) {
        if (source == null) {
            return null;
        }
        for (Gender gender : Gender.values()) {
            if (source.equalsIgnoreCase(gender.name())) {
                return gender;
            }
        }
        return null;
    }

    public static Attitude convertAttitude(String source) {
        if (source == null) {
            return null;
        }
        for (Attitude attitude : Attitude.values()) {
            if (source.equalsIgnoreCase(attitude.name())) {
                return attitude;
            }
        }
        return null;
    }

    public static ClientPriority convertClientPriority(String source) {
        if (source == null) {
            return null;
        }
        for (ClientPriority clientPriority : ClientPriority.values()) {
            if (source.equalsIgnoreCase(clientPriority.name())) {
                return clientPriority;
            }
        }
        return null;
    }

    public static ClientStatus convertClientStatus(String source) {
        if (source == null) {
            return null;
        }
        for (ClientStatus clientStatus : ClientStatus.values()) {
            if (source.equalsIgnoreCase(clientStatus.name())) {
                return clientStatus;
            }
        }
        return null;
    }

    public static HealthStatus convertHealthStatus(String source) {
        if (source == null) {
            return null;
        }
        for (HealthStatus healthStatus : HealthStatus.values()) {
            if (source.equalsIgnoreCase(healthStatus.name())) {
                return healthStatus;
            }
        }
        return null;
    }

    public static HouseAttitude convertHouseAttitude(String source) {
        if (source == null) {
            return null;
        }
        for (HouseAttitude houseAttitude : HouseAttitude.values()) {
            if (source.equalsIgnoreCase(houseAttitude.name())) {
                return houseAttitude;
            }
        }
        return null;
    }


    public static HousingStage convertHousingStage(String source) {
        if (source == null) {
            return null;
        }
        for (HousingStage housingStage : HousingStage.values()) {
            if (source.equalsIgnoreCase(housingStage.name())) {
                return housingStage;
            }
        }
        return null;
    }

    public static MentalStatus convertMentalStatus(String source) {
        if (source == null) {
            return null;
        }
        for (MentalStatus mentalStatus : MentalStatus.values()) {
            if (source.equalsIgnoreCase(mentalStatus.name())) {
                return mentalStatus;
            }
        }
        return null;
    }


    public static Need convertNeed(String source) {
        if (source == null) {
            return null;
        }
        for (Need need : Need.values()) {
            if (source.equalsIgnoreCase(need.name())) {
                return need;
            }
        }
        return null;
    }


    public static Pregnant convertPregnant(String source) {
        if (source == null) {
            return null;
        }
        for (Pregnant pregnant : Pregnant.values()) {
            if (source.equalsIgnoreCase(pregnant.toString())) {
                return pregnant;
            }
        }
        return Pregnant.NA;
    }


    public static SubstanceAbuse convertSubstanceAbuse(String source) {
        if (source == null) {
            return null;
        }
        for (SubstanceAbuse substanceAbuse : SubstanceAbuse.values()) {
            if (source.equalsIgnoreCase(substanceAbuse.name())) {
                return substanceAbuse;
            }
        }
        return null;
    }

    public static Violent convertViolent(String source) {
        if (source == null) {
            return null;
        }
        for (Violent violent : Violent.values()) {
            if (source.equalsIgnoreCase(violent.toString())) {
                return violent;
            }
        }
        return null;
    }

    public static Acquired convertAcquired(String source) {
        if (source == null) {
            return null;
        }
        for (Acquired acquired : Acquired.values()) {
            if (source.equalsIgnoreCase(acquired.toString())) {
                return acquired;
            }
        }
        return null;
    }

}