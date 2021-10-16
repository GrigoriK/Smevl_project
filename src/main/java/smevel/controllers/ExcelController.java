package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smevel.services.impl.ExcelService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/excel")
public class ExcelController {

    private final ExcelService excelService;

    @RequestMapping(path = "/emptyExcelVL/{type}", method = RequestMethod.GET)
    public ResponseEntity createEmptyVL(@PathVariable("type") String type) {
        return new ResponseEntity<>(excelService.getEmptyExcel(), HttpStatus.OK);

    }

}
