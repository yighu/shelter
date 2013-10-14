package com.ffg.shelter.view;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorMessage {

    private Map<String, String> errors;

    public ErrorMessage() {
        this(new LinkedHashMap<String, String>());
    }

    public ErrorMessage(Map<String, String> errors) {
        this.errors = errors;
    }

    public void addError(String name, String message) {
        errors.put(name, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}