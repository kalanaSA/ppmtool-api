package com.kalanasolutions.PPMToolapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UpdateProjectDto implements Serializable {

    private static final long serialVersionUID = -9083258738579359959L;

    private Long id;

    private String name;

    private String projectIdentifier;

    private String description;

    private Date startDate;

    private Date endDate;

    private Date createdAt;

}
