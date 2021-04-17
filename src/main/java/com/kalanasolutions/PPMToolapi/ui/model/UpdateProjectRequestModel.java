package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class
UpdateProjectRequestModel implements Serializable {

    private static final long serialVersionUID = -4785372776913758985L;

    private Long id;

    @NotBlank(message = "project name is required!")
    @Size(max = 20, message = "project name shouldn't be exceed 20 characters")
    private String name;

    private String projectIdentifier;

    @Size(max = 200, message = "project description shouldn't be exceed 200 characters")
    private String description;

    private Date startDate;

    private Date endDate;

    private Date createdAt;
}
