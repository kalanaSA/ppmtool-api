package com.kalanasolutions.PPMToolapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class CreateProjectDto implements Serializable {

    private static final long serialVersionUID = -1754578886992206238L;

    private String name;

    @NotBlank(message = "project identifier shouldn't be empty")
    private String projectIdentifier;

    private String description;

    private Date startDate;

    private Date endDate;

    public void setProjectIdentifier() {
        int nameLength = this.getName().length();
        String uuid = UUID.randomUUID().toString();
        this.projectIdentifier = this.getName().substring(0, Math.min(nameLength, 4)).toUpperCase().concat(uuid);
    }

}
