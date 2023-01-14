package org.example.readers;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class CSV {

    private CSV() {
    }

    public static <T> Optional<List<T>> getPojoCsvList(final String filePath, final Class<T> t, final CsvSchema schema) throws IOException {
        final CsvMapper csvMapper = CsvMapper.csvBuilder().build();
        Optional<List<T>> optionalTList;

        try(MappingIterator<T> iterator = csvMapper.readerFor(t).with(schema).readValues(new File(filePath))){
            optionalTList = Optional.of(iterator.readAll());
        }
        return optionalTList;
    }

    public static final Supplier<CsvSchema> getPgObjCSVSchema = () -> CsvSchema.builder().addColumn("ELEMENT_NAME").addColumn("TYPE")
            .addColumn("LOCATOR_VALUE").setSkipFirstDataRow(true).setColumnSeparator('|').build();

}
