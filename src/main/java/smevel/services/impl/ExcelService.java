package smevel.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import smevel.constants.StringConstants;
import smevel.dto.excel.ExcelOutputData;
import smevel.dto.excel.collector.AllVacationReportDataCollector;
import smevel.dto.excel.data.AllVacationReportData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ExcelService {

    private final AllVacationReportDataCollector allVacationReportDataCollector;

    public ExcelOutputData getEmptyExcel() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Test");

        Row row = sheet.createRow(0);
        createStringCellUnderRow(row, "Name");
        createStringCellUnderRow(row, "Surname");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            book.write(stream);
            book.close();
            return ExcelOutputData.builder()
                    .excelContent(stream.toByteArray())
                    .excelFileName("emptyExcelFile")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return ExcelOutputData.builder().build();
        }

    }

    public ExcelOutputData getAllListOfVacations() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("All vacations");
        createRowWithTitlesUnderSheet(sheet, 1,
                StringConstants.allListOfVacations);
        createAllVLDataRowsUnderSheet(getDateCellStyle(book), sheet, 2);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            book.write(stream);
            book.close();
            return ExcelOutputData.builder()
                    .excelContent(stream.toByteArray())
                    .excelFileName("allVacations")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return ExcelOutputData.builder().build();
        }
    }

    private void createRowWithTitlesUnderSheet(Sheet sheet, int rowNumber, Collection<String> titleList) {
        Row row = sheet.createRow(rowNumber);
        titleList.forEach(title -> createStringCellUnderRow(row, title));
    }

    private void createAllVLDataRowsUnderSheet(CellStyle styleDate, Sheet sheet, int startRowNumber) {
        Collection<AllVacationReportData> allVacationReportDataCollection = allVacationReportDataCollector.collectData();
        for (AllVacationReportData data : allVacationReportDataCollection) {
            Row row = sheet.createRow(startRowNumber);
            createStringCellUnderRow(row, data.getNameAndSurname());
            createDateCellUnderRow(styleDate, row, data.getVlStartDate());
            createDateCellUnderRow(styleDate, row, data.getVlEndDate());
            createIntCellUnderRow(row, data.getVlDuration());
            createStringCellUnderRow(row, data.getPositionName());
            startRowNumber++;
        }
    }


    private void createStringCellUnderRow(Row row, String cellValue) {
        Cell cell = row.createCell(getLastCellNumber(row));
        cell.setCellValue(cellValue);
    }

    private void createDateCellUnderRow(CellStyle styleDate, Row row, Date cellValue) {
        Cell cell = row.createCell(getLastCellNumber(row));
        cell.setCellValue(cellValue);
        cell.setCellStyle(styleDate);
    }

    private void createIntCellUnderRow(Row row, int cellValue) {
        Cell cell = row.createCell(getLastCellNumber(row));
        cell.setCellValue(cellValue);
    }

    private int getLastCellNumber(Row row) {
        short lastCellNum = row.getLastCellNum();
        if (lastCellNum < 0) {
            lastCellNum = 0;
        }
        return lastCellNum;
    }

    private CellStyle getDateCellStyle(Workbook book) {
        CreationHelper creationHelper = book.getCreationHelper();
        CellStyle styleDate = book.createCellStyle();
        styleDate.setDataFormat(creationHelper.createDataFormat().getFormat(
                StringConstants.DATE_FORMAT));
        return styleDate;
    }
}
