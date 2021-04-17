package com.kalanasolutions.PPMToolapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProjectNameException extends RuntimeException {

    private static final long serialVersionUID = -7687526641667125316L;
    private final HttpStatus status;

    public ProjectNameException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
