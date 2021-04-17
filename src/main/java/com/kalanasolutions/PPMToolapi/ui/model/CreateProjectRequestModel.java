package com.kalanasolutions.PPMToolapi.ui.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CreateProjectRequestModel implements Serializable {

    private static final long serialVersionUID = -8720086186767406927L;

    @NotBlank(message = "project name is required!")
    @Size(max = 20, message = "project name shouldn't be exceed 20 characters")
    private String name;

    @Size(max = 200, message = "project description shouldn't be exceed 200 characters")
    private String description;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    //@JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

}