package frameworktests;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.example.framework.Constants;
import org.example.pojos.PgObjCSV;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.example.readers.CSV.getPojoCsvList;
import static org.example.writers.CSV.toCSV;

@Test
public final class CSVWritersTests {

    final static String PATH_TO_WRITE = Constants.PATH_TEST_RC + "CSVFiles" + File.separator + "writer.csv";
    final static String PATH_TO_READ = Constants.PATH_TEST_RC + "CSVFiles" + File.separator + "test.csv";

    public void csvWriterTestOne() throws IOException {
        val list = getPojoCsvList(PATH_TO_READ, PgObjCSV.class);
        Assertions.assertThat(list).isPresent();
        list.get().forEach(System.out::println);
        toCSV(PATH_TO_WRITE, list.get(), PgObjCSV.class);

    }
}
