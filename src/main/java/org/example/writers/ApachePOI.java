package org.example.writers;

import lombok.val;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public final class ApachePOI {

    private ApachePOI() {
    }

    public static synchronized void writeToXlsx(final String fileName, final String sheetName, int row, int col, final String value) throws IOException, InvalidFormatException {
       try(val fos = new FileOutputStream(fileName);val workbook = new XSSFWorkbook()){
            val sheet = workbook.createSheet(sheetName);
            val poiRow = sheet.createRow(row);
            val poiCol = poiRow.createCell(col, CellType.STRING);
            poiCol.setCellValue(value);
            workbook.write(fos);
       }
    }
}
