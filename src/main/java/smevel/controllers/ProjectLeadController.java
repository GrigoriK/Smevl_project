package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smevel.beans.ProjectLeadBean;
import smevel.beans.inputBean.InputProjectLeadBean;
import smevel.beans.outputBean.OutputProjectBean;
import smevel.beans.outputBean.OutputProjectLeadBean;
import smevel.services.impl.ProjectLeadService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/projectLead")
public class ProjectLeadController {

    private final ProjectLeadService projectLeadService;

    @RequestMapping(path = "/addProjectLead", method = RequestMethod.POST)
    public ResponseEntity<OutputProjectLeadBean> addProjectLead(@RequestBody InputProjectLeadBean inputProjectLeadBean) {
        return projectLeadService.addNewEntityWithResponse(inputProjectLeadBean);
    }

    @RequestMapping(path = "/getProjectLeadById", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputProjectLeadBean>> getProjectLeadById(
            @RequestParam("projectLeadId") String projectLeadId) {
        return projectLeadService.getEntityByIdWithResponse(projectLeadId);
    }

    @RequestMapping(path = "/getAllProjectLeads", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputProjectLeadBean>> getAllProjectLeads() {
        return projectLeadService.getAllEntitiesWithResponse();
    }

    @RequestMapping(path = "/madeEmployeeLeadOfProject", method = RequestMethod.GET)
    public ResponseEntity<OutputProjectLeadBean> assignPositionToEmployee(@RequestParam("employeeId") String employeeId,
                                                                    @RequestParam("projectId") String projectId) {
        return projectLeadService.madeEmployeeLeadOfProject(employeeId, projectId);
    }

    @RequestMapping(path = "/getLeadOfProjectByProjectId", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputProjectLeadBean>> getLeadOfProjectByProjectId(@RequestParam("projectId") String projectId) {
        return projectLeadService.getProjectLeadByProjectId(projectId);
    }


}
