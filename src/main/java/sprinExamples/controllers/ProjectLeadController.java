package sprinExamples.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprinExamples.beans.ProjectLeadBean;
import sprinExamples.beans.inputBean.InputProjectLeadBean;
import sprinExamples.services.impl.ProjectLeadService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/v1/projectLead")
public class ProjectLeadController {

    private final ProjectLeadService projectLeadService;

    @RequestMapping(path = "/addProjectLead", method = RequestMethod.POST)
    public ResponseEntity<ProjectLeadBean> addProjectLead(@RequestBody InputProjectLeadBean inputProjectLeadBean) {
        return projectLeadService.addNewEntityWithResponse(inputProjectLeadBean);
    }

    @RequestMapping(path = "/getProjectLeadById", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectLeadBean>> getProjectLeadById(
            @RequestParam("projectLeadId") String projectLeadId) {
        return projectLeadService.getEntityByIdWithResponse(projectLeadId);
    }

    @RequestMapping(path = "/getAllProjectLeads", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectLeadBean>> getAllProjectLeads() {
        return projectLeadService.getAllEntitiesWithResponse();
    }

    @RequestMapping(path = "/madeEmployeeLeadOfProject", method = RequestMethod.GET)
    public ResponseEntity<ProjectLeadBean> assignPositionToEmployee(@RequestParam("employeeId") String employeeId,
                                                                    @RequestParam("projectId") String projectId) {
        return projectLeadService.madeEmployeeLeadOfProject(employeeId, projectId);
    }

    @RequestMapping(path = "/getLeadOfProjectByProjectId", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProjectLeadBean>> getLeadOfProjectByProjectId(@RequestParam("projectId") String projectId) {
        return projectLeadService.getProjectLeadByProjectId(projectId);
    }


}
