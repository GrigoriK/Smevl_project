package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smevel.beans.EmployeeBean;
import smevel.beans.inputBean.InputEmployeeBean;
import smevel.beans.outputBean.OutputEmployeeBean;
import smevel.services.impl.EmployeeService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<OutputEmployeeBean> addEmployee(@RequestBody InputEmployeeBean inputEmployeeBean) {
        return employeeService.addNewEntityWithResponse(inputEmployeeBean);
    }

    @RequestMapping(path = "/getEmployeeById", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputEmployeeBean>> getEmployeeById(@RequestParam("employeeId") String employeeId) {
        return employeeService.getEntityByIdWithResponse(employeeId);
    }

    @RequestMapping(path = "/getAllEmployees", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputEmployeeBean>> getAllEmployees() {
        return employeeService.getAllEntitiesWithResponse();
    }

    @RequestMapping(path = "/assignEmployeeToTheProject", method = RequestMethod.GET)
    public ResponseEntity<OutputEmployeeBean> assignEmployeeToTheProject(@RequestParam("employeeId") String employeeId,
                                                                   @RequestParam("projectId") String projectId) {
        return employeeService.assignEmployeeToProject(employeeId, projectId);
    }

    @RequestMapping(path = "/assignPositionToEmployee", method = RequestMethod.GET)
    public ResponseEntity<OutputEmployeeBean> assignPositionToEmployee(@RequestParam("employeeId") String employeeId,
                                                                 @RequestParam("positionId") String positionId) {
        return employeeService.assignPositionToEmployee(employeeId, positionId);
    }


}
