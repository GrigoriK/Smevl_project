package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smevel.beans.VacationLeaveBean;
import smevel.beans.inputBean.InputVacationLeaveBean;
import smevel.services.impl.VacationLeaveService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/vacationLeave")
public class VocationLeaveController {

    private final VacationLeaveService vacationLeaveService;

    @RequestMapping(path = "/addVacationLeave", method = RequestMethod.POST)
    public ResponseEntity<VacationLeaveBean> addVacationLeave(@RequestBody InputVacationLeaveBean inputVacationLeaveBean) {
        return vacationLeaveService.addNewEntityWithResponse(inputVacationLeaveBean);
    }

    @RequestMapping(path = "/getVacationLeaveById", method = RequestMethod.GET)
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationLeaveById(@RequestParam("vacationLeaveId") String vacationLeaveId) {
        return vacationLeaveService.getEntityByIdWithResponse(vacationLeaveId);
    }

    @RequestMapping(path = "/getAllVacationLeaves", method = RequestMethod.GET)
    public ResponseEntity<Collection<VacationLeaveBean>> getAllVacationLeaves() {
        return vacationLeaveService.getAllEntitiesWithResponse();
    }

    @RequestMapping(path = "/getVacationsBetweenDateRange", method = RequestMethod.GET)
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsBetweenDateRange(@RequestParam(name = "startDate") String startDate,
                                                                                      @RequestParam("endDate") String endDate) {
        return vacationLeaveService.getVacationsByRange(startDate, endDate);
    }


    @RequestMapping(path = "/getVacationsByEmployeeId", method = RequestMethod.GET)
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsByEmployeeId(@RequestParam(name = "employeeId") String employeeId) {
        return vacationLeaveService.getVacationsListByEmployeeId(employeeId);
    }


    @RequestMapping(path = "/getVacationsByProjectId", method = RequestMethod.GET)
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsByProjectId(@RequestParam(name = "projectId") String projectId) {
        return vacationLeaveService.getVacationsListByProjectId(projectId);
    }


}
