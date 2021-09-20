package sprinExamples.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprinExamples.beans.ProjectBean;
import sprinExamples.beans.inputBean.InputProjectBean;
import sprinExamples.services.impl.ProjectService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping(path = "/addProject", method = RequestMethod.POST)
    public ResponseEntity<ProjectBean> addProject(@RequestBody InputProjectBean inputProjectBean) {
        return projectService.addNewEntityWithResponse(inputProjectBean);
    }

    @RequestMapping(path = "/getProjectById", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectBean>> getProjectById(@RequestParam("projectId") String projectId) {
        return projectService.getEntityByIdWithResponse(projectId);
    }

    @RequestMapping(path = "/getProjectByName", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectBean>> getProjectByName(@RequestParam("projectName") String projectName) {
        return projectService.getProjectByName(projectName);
    }

    @RequestMapping(path = "/getProjectByCode", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectBean>> getProjectByCode(@RequestParam("projectCode") String projectCode) {
        return projectService.getProjectByCode(projectCode);
    }

    @RequestMapping(path = "/getProjects", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectBean>> getAllProjects() {
        return projectService.getAllEntitiesWithResponse();
    }


}
