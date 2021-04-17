package com.kalanasolutions.PPMToolapi.ui.controller;

import com.kalanasolutions.PPMToolapi.exception.ProjectNameException;
import com.kalanasolutions.PPMToolapi.exception.ProjectServiceException;
import com.kalanasolutions.PPMToolapi.ui.model.ExceptionErrorMsgResponse;
import com.kalanasolutions.PPMToolapi.ui.model.ExceptionProjectNameErrorMsgResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
@Log4j2
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MSG = "exception found: {}";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception ex, WebRequest webRequest) {

        String errorMsgDescription = ex.getLocalizedMessage();
        if (errorMsgDescription == null) errorMsgDescription = ex.toString();
        log.error(MSG, errorMsgDescription);

        ExceptionErrorMsgResponse errorObject = new ExceptionErrorMsgResponse(new Date(), errorMsgDescription);
        return new ResponseEntity<>(errorObject, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProjectServiceException.class)
    public ResponseEntity<ExceptionErrorMsgResponse> handleProjectServiceException(ProjectServiceException e,
                                                                                   WebRequest webRequest) {

        String errorMsgDescription = e.getLocalizedMessage();
        if (errorMsgDescription == null) errorMsgDescription = e.toString();
        log.error(MSG, errorMsgDescription);

        ExceptionErrorMsgResponse errorObject = new ExceptionErrorMsgResponse(new Date(), errorMsgDescription);
        HttpStatus status = e.getStatus();

        return new ResponseEntity<ExceptionErrorMsgResponse>(errorObject, new HttpHeaders(), status);

    }

    @ExceptionHandler(value = ProjectNameException.class)
    public ResponseEntity<ExceptionProjectNameErrorMsgResponse> handleProjectNameException(ProjectNameException e,
                                                                                           WebRequest webRequest) {

        String errorMsgDescription = e.getLocalizedMessage();
        if (errorMsgDescription == null) errorMsgDescription = e.toString();
        log.error(MSG, errorMsgDescription);

        ExceptionProjectNameErrorMsgResponse errorObject = new ExceptionProjectNameErrorMsgResponse(
                new Date(), errorMsgDescription);
        HttpStatus status = e.getStatus();

        return new ResponseEntity<ExceptionProjectNameErrorMsgResponse>(errorObject, new HttpHeaders(), status);

    }

}