package org.example.readers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.framework.Constants;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CSV {

    private final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "CSV" + File.separator;

    private CSV() {
    }

    public static <T> Optional<List<T>> getPojoCsvList(final String fileName, final Class<T> t) throws IOException {

        String path = PATH_TO_DIR + fileName + ".csv";
        final CsvMapper csvMapper = new CsvMapper();
        final CsvSchema schema = csvMapper.schemaFor(t).withSkipFirstDataRow(true).withColumnSeparator('|');
        Optional<List<T>> optionalTList;

        try(MappingIterator<T> iterator = csvMapper.readerFor(t).with(schema).readValues(new File(path))){
            optionalTList = Optional.of(iterator.readAll());
        }
        return optionalTList;
    }

}
