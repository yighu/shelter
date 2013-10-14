package com.ffg.shelter.advice;

import com.ffg.shelter.exception.AuthorizationException;
import com.ffg.shelter.exception.BusinessException;
import com.ffg.shelter.exception.ValidationErrors;
import com.ffg.shelter.view.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerExceptionResolver extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Inject
    @Named("messageSource")
    public ExceptionHandlerExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {
            BusinessException.class,
            Exception.class,
            Throwable.class,
            AuthorizationException.class
    })
    public ErrorMessage handleCustomException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.addError("error", ex.getMessage());

        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            System.out.println(stackTraceElement.toString() + "\n");
        }
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {
            ValidationErrors.class
    })
    public ErrorMessage handleCustomException(ValidationErrors ex, WebRequest request) {
        return new ErrorMessage(ex.getErrors());
    }


}