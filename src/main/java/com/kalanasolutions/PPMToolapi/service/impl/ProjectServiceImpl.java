package com.kalanasolutions.PPMToolapi.service.impl;

import com.kalanasolutions.PPMToolapi.domain.Project;
import com.kalanasolutions.PPMToolapi.dto.CreateProjectDto;
import com.kalanasolutions.PPMToolapi.dto.UpdateProjectDto;
import com.kalanasolutions.PPMToolapi.exception.ProjectNameException;
import com.kalanasolutions.PPMToolapi.exception.ProjectServiceException;
import com.kalanasolutions.PPMToolapi.repository.ProjectRepo;
import com.kalanasolutions.PPMToolapi.service.ProjectService;
import com.kalanasolutions.PPMToolapi.ui.model.ErrorMessages;
import com.kalanasolutions.PPMToolapi.ui.model.ProjectResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ProjectResponseModel createProject(CreateProjectDto createProjectDto) {

        String projectName = createProjectDto.getName();
        Optional<Project> isProject = projectRepo.findByName(projectName);
        if (isProject.isPresent()) {
            throw new ProjectNameException(
                    projectName + " " + ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMsg(),
                    HttpStatus.BAD_REQUEST);
        }

        createProjectDto.setProjectIdentifier(); //set uuid
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Project persistProject = modelMapper.map(createProjectDto, Project.class);

        Project createdProject = projectRepo.save(persistProject);
        return modelMapper.map(createdProject, ProjectResponseModel.class);

    }

    @Override
    public List<ProjectResponseModel> getProjects(int page, int limit, String sort) {

        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.valueOf(sort), "name");
        Page<Project> projectListPage = projectRepo.findAll(pageable);
        List<Project> projectList = projectListPage.getContent();
        Type projectResponseModelList = new TypeToken<List<ProjectResponseModel>>() {
        }.getType();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(projectList, projectResponseModelList);

    }

    @Override
    public ProjectResponseModel getProject(String projectId) {

        Project project = projectRepo.findByProjectIdentifier(projectId)
                .orElseThrow(() -> new ProjectServiceException(
                        ErrorMessages.NO_RECORD_FOUND.getErrorMsg() + " for id : " + projectId,
                        HttpStatus.NOT_FOUND));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(project, ProjectResponseModel.class);

    }

    @Override
    public ProjectResponseModel updateProject(String projectId, UpdateProjectDto updateProjectDto) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Project persistProject = modelMapper.map(updateProjectDto, Project.class);

        Project updatedProject = projectRepo.findByProjectIdentifier(projectId)
                .map(project -> projectRepo.save(persistProject))
                .orElseThrow(() -> new ProjectServiceException(
                        ErrorMessages.NO_RECORD_FOUND.getErrorMsg() + " for id : " + projectId + " to update",
                        HttpStatus.NOT_FOUND));

        return modelMapper.map(updatedProject, ProjectResponseModel.class);
    }

    @Override
    public Long deleteProject(String projectId) {

        return projectRepo.findByProjectIdentifier(projectId)
                .map(project -> projectRepo.deleteByProjectIdentifier(projectId))
                .orElseThrow(() -> new ProjectServiceException(
                        ErrorMessages.NO_RECORD_FOUND.getErrorMsg() + " for this id : " + projectId + " to delete",
                        HttpStatus.BAD_REQUEST));
    }


}
