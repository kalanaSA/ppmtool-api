package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ProjectResponseModel implements Serializable {

    private static final long serialVersionUID = 5106818666000733490L;

    private String name;

    private String projectIdentifier;

    private String description;

    private Date startDate;

    private Date endDate;

    private Date createdAt;

    private Date updatedAt;

}
