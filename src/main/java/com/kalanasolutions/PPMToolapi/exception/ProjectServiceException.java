package com.kalanasolutions.PPMToolapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProjectServiceException extends RuntimeException {

    private static final long serialVersionUID = -8534851000114877801L;
    private final HttpStatus status;

    public ProjectServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}