package smevel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smevel.services.impl.ExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/excel")
public class ExcelController {

    private final ExcelService excelService;

    @RequestMapping(path = "/emptyExcelVL", method = RequestMethod.GET)
    public void createEmptyVL(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
        try {
            response.getOutputStream().write(excelService.getEmptyExcel());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
