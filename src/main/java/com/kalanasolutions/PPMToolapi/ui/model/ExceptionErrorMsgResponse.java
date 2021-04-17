package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;;

@AllArgsConstructor
@Getter
public class ExceptionErrorMsgResponse {

    private final Date timestamp;
    private final String errorMsg;

}
