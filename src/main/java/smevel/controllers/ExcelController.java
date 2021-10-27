package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smevel.dto.excel.ExcelOutputData;
import smevel.dto.excel.filter.DateRangeVacationFilter;
import smevel.services.impl.ExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/excel")
public class ExcelController {
    private final String CONTENT_DESCRIPTION = "attachment; filename=%s.xlsx";

    private final ExcelService excelService;

    @RequestMapping(path = "/allVacationsExcel", method = RequestMethod.GET)
    public void createAllVacationsReport(HttpServletResponse response) {
        try {
            ExcelOutputData allListOfVacations = excelService.getAllListOfVacations();
            response.getOutputStream().write(allListOfVacations.getExcelContent());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    String.format(CONTENT_DESCRIPTION, allListOfVacations.getExcelFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/getVacationsByDateRangeExcel", method = RequestMethod.GET)
    public void createAllVacationsReport(HttpServletResponse response,
                                         DateRangeVacationFilter filter) {
        try {
            ExcelOutputData listOfVacationsByDateRange = excelService.getListOfVacationsByDateRange(filter);
            response.getOutputStream().write(listOfVacationsByDateRange.getExcelContent());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    String.format(CONTENT_DESCRIPTION, listOfVacationsByDateRange.getExcelFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
