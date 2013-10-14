package com.ffg.shelter.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidationErrors extends /*MethodArgumentNotValidException*/Exception {

    private Map<String, String> errors = new LinkedHashMap<String, String>();

    public void addError(String name, String description) {
        errors.put(name, description);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return errors.toString();
    }

    public static void check(BindingResult result) throws ValidationErrors {
        if(result.hasErrors()){
            ValidationErrors ve = new ValidationErrors();
            for (FieldError error : result.getFieldErrors()) {
                ve.addError(error.getField(), error.getDefaultMessage());
            }
            throw ve;
        }
    }
}
