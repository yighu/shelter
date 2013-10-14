package com.ffg.shelter.converter;

import com.ffg.shelter.model.Alert;
import com.ffg.shelter.model.CampBadge;
import com.ffg.shelter.model.CampStatus;
import com.ffg.shelter.model.CampType;


public class CampConverter {

    public static CampBadge convertCampBadge(String source) {

        for (CampBadge campBadge : CampBadge.values()) {
            if (source.equalsIgnoreCase(campBadge.name())) {
                return campBadge;
            }
        }
        return null;
    }

    public static CampStatus convertCampStatus(String source) {

        for (CampStatus campStatus : CampStatus.values()) {
            if (source.equalsIgnoreCase(campStatus.name())) {
                return campStatus;
            }
        }
        return null;
    }

    public static CampType convertCampType(String source) {

        for (CampType campType : CampType.values()) {
            if (source.equalsIgnoreCase(campType.toString())) {
                return campType;
            }
        }
        return null;
    }

    public static Alert convertCampAlert(String source) {

        for (Alert alert : Alert.values()) {
            if (source.equalsIgnoreCase(alert.toString())) {
                return alert;
            }
        }
        return null;
    }

}
