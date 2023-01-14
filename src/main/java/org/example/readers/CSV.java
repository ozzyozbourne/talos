package org.example.readers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CSV {

    private CSV() {
    }

    public static <T> Optional<List<T>> getPojoCsvList(final String filePath, final Class<T> t) throws IOException {
        final CsvMapper csvMapper = CsvMapper.csvBuilder().build();
        final CsvSchema schema = csvMapper.schemaFor(t).withSkipFirstDataRow(true).withColumnSeparator('|');
        Optional<List<T>> optionalTList;

        try(MappingIterator<T> iterator = csvMapper.readerFor(t).with(schema).readValues(new File(filePath))){
            optionalTList = Optional.of(iterator.readAll());
        }
        return optionalTList;
    }

}
