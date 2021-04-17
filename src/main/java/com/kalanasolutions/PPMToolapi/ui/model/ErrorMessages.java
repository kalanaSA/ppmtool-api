package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("no record found"),
    NOT_FOUND_ANY_RECORD("not found any record:"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address not verified");

    private String errorMsg;

    ErrorMessages(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
