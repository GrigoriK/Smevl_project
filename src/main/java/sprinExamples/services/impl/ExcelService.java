package sprinExamples.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ExcelService {
    public byte[] getEmptyExcel() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Test");

        Row row = sheet.createRow(0);

        Cell name = row.createCell(0);
        name.setCellValue("Test");

        Cell value = row.createCell(1);


        // Нумерация лет начинается с 1900-го
        value.setCellValue("Test");

        // Меняем размер столбца
        sheet.autoSizeColumn(1);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            book.write(stream);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }
}
