package com.kalanasolutions.PPMToolapi.service;

import com.kalanasolutions.PPMToolapi.dto.CreateProjectDto;
import com.kalanasolutions.PPMToolapi.dto.UpdateProjectDto;
import com.kalanasolutions.PPMToolapi.ui.model.ProjectResponseModel;

import java.util.List;

public interface ProjectService {

    ProjectResponseModel createProject(CreateProjectDto createProjectDto);

    List<ProjectResponseModel> getProjects(int page, int limit, String sort);

    ProjectResponseModel getProject(String projectId);

    ProjectResponseModel updateProject(String projectId, UpdateProjectDto updateProjectDto);

    Long deleteProject(String projectId);

}
