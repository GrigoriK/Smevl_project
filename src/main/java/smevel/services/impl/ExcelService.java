package smevel.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import smevel.constants.ExcelConstants;
import smevel.constants.StringConstants;
import smevel.dto.excel.ExcelOutputData;
import smevel.dto.excel.collector.AllVacationReportDataCollector;
import smevel.dto.excel.collector.VacationDateRangeReportDataCollector;
import smevel.dto.excel.data.BaseVacationLeaveData;
import smevel.dto.excel.filter.DateRangeVacationFilter;
import smevel.services.DateFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class ExcelService {

    private final AllVacationReportDataCollector allVacationReportDataCollector;
    private final VacationDateRangeReportDataCollector vacationDateRangeReportDataCollector;

    public ExcelOutputData getAllListOfVacations() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet(ExcelConstants.ALL_VACATIONS_REPORT_SHEET_NAME);
        createRowWithTitlesUnderSheet(sheet, 1,
                ExcelConstants.ALL_LIST_OF_VACATIONS_DATA_TITLES);
        createAllVLDataRowsUnderSheet(getDateCellStyle(book), sheet, 2,
                allVacationReportDataCollector::collectData);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            book.write(stream);
            book.close();
            return ExcelOutputData.builder()
                    .excelContent(stream.toByteArray())
                    .excelFileName(String.format(ExcelConstants.ALL_VACATIONS_REPORT_FILE_NAME,
                            DateFormatter.getFormattedStringFoReport()))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return ExcelOutputData.builder().build();
        }
    }

    public ExcelOutputData getListOfVacationsByDateRange(DateRangeVacationFilter filter) {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet(ExcelConstants.VACATIONS_BY_DATE_REPORT_SHEET_NAME);
        CellStyle dateCellStyle = getDateCellStyle(book);
        createDateRangeRows(filter, sheet, 0, 0, dateCellStyle,
                ExcelConstants.DATE_RANGES_TITLES);
        createRowWithTitlesUnderSheet(sheet, 6,
                ExcelConstants.ALL_LIST_OF_VACATIONS_DATA_TITLES);
        createAllVLDataRowsUnderSheet(dateCellStyle, sheet, 7,
                () -> vacationDateRangeReportDataCollector.collectData(filter));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            book.write(stream);
            book.close();
            return ExcelOutputData.builder()
                    .excelContent(stream.toByteArray())
                    .excelFileName(String.format(ExcelConstants.VACATIONS_BY_DATE_REPORT_FILE_NAME,
                            DateFormatter.getFormattedStringFoReport()))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return ExcelOutputData.builder().build();
        }
    }

    private void createRowWithTitlesUnderSheet(Sheet sheet, int rowNumber, Collection<String> titleList) {
        Row row = sheet.createRow(rowNumber);
        titleList.forEach(title -> createStringCellUnderRow(row, getLastCellNumber(row), title));
    }

    private void createAllVLDataRowsUnderSheet(CellStyle styleDate, Sheet sheet, int startRowNumber,
                                               Supplier<Collection<? extends BaseVacationLeaveData>> reportDataSupplier) {
        Collection<? extends BaseVacationLeaveData> baseVacationLeaveData = reportDataSupplier.get();
        for (BaseVacationLeaveData data : baseVacationLeaveData) {
            Row row = sheet.createRow(startRowNumber);
            createStringCellUnderRow(row, getLastCellNumber(row), data.getNameAndSurname());
            createDateCellUnderRow(styleDate, row, getLastCellNumber(row), data.getVlStartDate());
            createDateCellUnderRow(styleDate, row, getLastCellNumber(row), data.getVlEndDate());
            createIntCellUnderRow(row, getLastCellNumber(row), data.getVlDuration());
            createStringCellUnderRow(row, getLastCellNumber(row), data.getPositionName());
            createStringCellUnderRow(row, getLastCellNumber(row), data.getProjectName());
            startRowNumber++;
        }
    }

    private void createDateRangeRows(DateRangeVacationFilter filter, Sheet sheet,
                                     int rowStartNumber, int cellStartNumber,
                                     CellStyle dateStyle,
                                     ArrayList<String> titleList) {
        Row startRangeRow = sheet.createRow(rowStartNumber);
        createStringCellUnderRow(startRangeRow, cellStartNumber, titleList.get(0));
        createDateCellUnderRow(dateStyle, startRangeRow, cellStartNumber+1,
                DateFormatter.getDateByFormattedStringBy(filter.getVacationStartDate()));

        Row endRangeRow = sheet.createRow(++rowStartNumber);
        createStringCellUnderRow(endRangeRow, cellStartNumber, titleList.get(1));
        createDateCellUnderRow(dateStyle, endRangeRow, cellStartNumber+1,
                DateFormatter.getDateByFormattedStringBy(filter.getVacationEndDate()));

    }


    private void createStringCellUnderRow(Row row, int cellNumber, String cellValue) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellValue(cellValue);
    }

    private void createDateCellUnderRow(CellStyle styleDate, Row row, int cellNumber, Date cellValue) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellValue(cellValue);
        cell.setCellStyle(styleDate);
    }

    private void createIntCellUnderRow(Row row, int cellNumber, int cellValue) {
        Cell cell = row.createCell(cellNumber);
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
