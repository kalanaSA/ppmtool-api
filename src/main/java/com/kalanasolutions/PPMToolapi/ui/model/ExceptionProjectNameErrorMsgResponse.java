package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionProjectNameErrorMsgResponse {

    private final Date timestamp;
    private final String name;

}
