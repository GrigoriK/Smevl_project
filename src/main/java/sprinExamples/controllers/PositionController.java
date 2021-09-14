package sprinExamples.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprinExamples.beans.PositionBean;
import sprinExamples.beans.inputBean.InputPositionBean;
import sprinExamples.services.impl.PositionService;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/v1/position")
public class PositionController {

    private final PositionService positionService;

    @RequestMapping(path = "/addPosition", method = RequestMethod.POST)
    public ResponseEntity<PositionBean> addPosition(@RequestBody InputPositionBean inputPositionBean) {
        return positionService.addNewEntityWithResponse(inputPositionBean);
    }

    @RequestMapping(path = "/getPositionById", method = RequestMethod.GET)
    public ResponseEntity<Collection<PositionBean>> getProjectById(@RequestParam("positionId") String positionId) {
        return positionService.getEntityByIdWithResponse(positionId);
    }

    @RequestMapping(path = "/getPositionByName", method = RequestMethod.GET)
    public ResponseEntity<Collection<PositionBean>> getPositionByName(@RequestParam("positionName") String positionName) {
        return positionService.getPositionByName(positionName);
    }


    @RequestMapping(path = "/getAllPositions", method = RequestMethod.GET)
    public ResponseEntity<Collection<PositionBean>> getAllPositions() {
        return positionService.getAllEntitiesWithResponse();
    }


}
