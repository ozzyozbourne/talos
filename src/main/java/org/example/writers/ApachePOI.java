package org.example.writers;

import com.google.common.collect.Multimap;
import lombok.val;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class ApachePOI {

    private ApachePOI() {
    }

    public static void writeToNewXlsx(final String fileName, final String sheetName, final Multimap<Integer, String> valueMap) throws IOException {
       try(val fos = new FileOutputStream(fileName);val workbook = new XSSFWorkbook()){
           writeMapToSheet(workbook, valueMap, sheetName);
           workbook.write(fos);
       }
    }

    public static synchronized void updateCellXlsx(final String fileName, final String sheetName, int row, int col, final String value) throws IOException {
        val fis = new FileInputStream(fileName);
        val workbook = WorkbookFactory.create(fis);
        workbook.getSheet(sheetName).getRow(row)
                .createCell(col, CellType.STRING).setCellValue(value);
        clean(workbook, fis ,fileName);
    }

    public static synchronized void updateRowXlsx(final String fileName, final String sheetName, int row, final List<String> colValue) throws IOException {
        val fis = new FileInputStream(fileName);
        val workbook = WorkbookFactory.create(fis);
        val poiRow = workbook.getSheet(sheetName).createRow(row);
        val atomicInt = new AtomicInteger(0);
        colValue.forEach(val -> poiRow.createCell(atomicInt.getAndIncrement(), CellType.STRING).setCellValue(val));
        clean(workbook, fis, fileName);
    }

    public static synchronized void updateSheetXlsx(final String fileName, final String sheetName,  final Multimap<Integer, String> valMap) throws IOException {
        val fis = new FileInputStream(fileName);
        val workbook = WorkbookFactory.create(fis);
        writeMapToSheet(workbook, valMap, sheetName);
        clean(workbook, fis, fileName);
    }

    private static void writeMapToSheet(final Workbook workbook, final Multimap<Integer, String> valMap, final String sheetName){
        val sheet = workbook.createSheet(sheetName);
        val map  = valMap.asMap();
        map.forEach((row, col) ->{val poiRow = sheet.createRow(row); val atomicInt = new AtomicInteger(0);
            col.forEach(c ->poiRow.createCell(atomicInt.getAndIncrement(), CellType.STRING).setCellValue(c));});
    }

    private static void clean(final Workbook workbook, final FileInputStream fis,  final String fileName) throws IOException {
        fis.close();
        val fos = new FileOutputStream(fileName);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

}
