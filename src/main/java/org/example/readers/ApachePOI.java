package org.example.readers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.val;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public final class ApachePOI {

    private ApachePOI() {
    }

    public static Multimap<Integer, String> readAllFromXlsx(final String fileName, final String sheetName) throws IOException {
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        try(val workbook = new XSSFWorkbook(new FileInputStream(fileName))){
           val sheet = workbook.getSheet(sheetName);
           sheet.forEach(r -> {val vr = r.getRowNum();
               r.forEach(c -> multimap.put(vr, c.getStringCellValue()));});
       }return multimap;
    }
}
