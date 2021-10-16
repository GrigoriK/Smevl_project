package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smevel.beans.PositionBean;
import smevel.beans.inputBean.InputPositionBean;
import smevel.beans.outputBean.OutputPositionBean;
import smevel.services.impl.PositionService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @RequestMapping(path = "/addPosition", method = RequestMethod.POST)
    public ResponseEntity<OutputPositionBean> addPosition(@RequestBody InputPositionBean inputPositionBean) {
        return positionService.addNewEntityWithResponse(inputPositionBean);
    }

    @RequestMapping(path = "/getPositionById", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputPositionBean>> getProjectById(@RequestParam("positionId") String positionId) {
        return positionService.getEntityByIdWithResponse(positionId);
    }

    @RequestMapping(path = "/getPositionByName", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputPositionBean>> getPositionByName(@RequestParam("positionName") String positionName) {
        return positionService.getPositionByName(positionName);
    }


    @RequestMapping(path = "/getAllPositions", method = RequestMethod.GET)
    public ResponseEntity<Collection<OutputPositionBean>> getAllPositions() {
        return positionService.getAllEntitiesWithResponse();
    }


}
