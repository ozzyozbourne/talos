package org.example.writers;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSV {

    public synchronized static  <T> void toCSV(final String filePath, List<T> tList, final Class<T> t) throws IOException {
        val mapper = new CsvMapper();
        val schema = mapper.schemaFor(t).withColumnSeparator('|').withHeader().withoutQuoteChar();
        mapper.writer(schema).writeValue(new File(filePath), tList);
    }

}
