package com.ffg.shelter.converter;

import com.ffg.shelter.model.Action;
import com.ffg.shelter.model.UserRole;


public class UserConverter {
    public static UserRole convertUserRole(String source) {
        if (source != null) {
            for (UserRole userRole : UserRole.values()) {
                if (source.equalsIgnoreCase(userRole.toString())) {
                    return userRole;
                }
            }
        }
        return null;
    }

    public static Action convertAction(String source) {
        if (source != null) {
            for (Action action : Action.values()) {
                if (source.equalsIgnoreCase(action.toString())) {
                    return action;
                }
            }
        }
        return null;
    }
}
