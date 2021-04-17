package com.kalanasolutions.PPMToolapi.ui.controller;

import com.kalanasolutions.PPMToolapi.dto.CreateProjectDto;
import com.kalanasolutions.PPMToolapi.dto.UpdateProjectDto;
import com.kalanasolutions.PPMToolapi.service.ProjectService;
import com.kalanasolutions.PPMToolapi.service.impl.MapValidationErrorService;
import com.kalanasolutions.PPMToolapi.ui.model.CreateProjectRequestModel;
import com.kalanasolutions.PPMToolapi.ui.model.ProjectResponseModel;
import com.kalanasolutions.PPMToolapi.ui.model.UpdateProjectRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    private static final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody CreateProjectRequestModel createProject,
                                           BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.validationError(result);
        if (errorMap != null) return errorMap;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CreateProjectDto projectDto = modelMapper.map(createProject, CreateProjectDto.class);

        ProjectResponseModel returnedProject = projectService.createProject(projectDto);
        return new ResponseEntity<ProjectResponseModel>(returnedProject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseModel>> getProjects(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                                  @RequestParam(value = "sort", defaultValue = "ASC") String sort) {
        List<ProjectResponseModel> returnedProjectList = projectService.getProjects(page, limit, sort);
        return new ResponseEntity<List<ProjectResponseModel>>(returnedProjectList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectResponseModel> getProject(@PathVariable(name = "id") String projectId) {
        ProjectResponseModel returnedProject = projectService.getProject(projectId);
        return new ResponseEntity<ProjectResponseModel>(returnedProject, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProject(@PathVariable(name = "id") String projectId,
                                           @Valid @RequestBody UpdateProjectRequestModel updateProject,
                                           BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validationError(result);
        if (errorMap != null) return errorMap;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UpdateProjectDto projectDto = modelMapper.map(updateProject, UpdateProjectDto.class);
        ProjectResponseModel returnedProject = projectService.updateProject(projectId, projectDto);
        return new ResponseEntity<ProjectResponseModel>(returnedProject, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable(name = "id") String projectId) {
        Long count = projectService.deleteProject(projectId);
        return new ResponseEntity<String>((count + " record affected"), HttpStatus.OK);
    }

}
