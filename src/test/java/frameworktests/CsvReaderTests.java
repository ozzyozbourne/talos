package frameworktests;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.example.framework.Constants;
import org.example.pojos.PgObjCSV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.example.readers.CSV.getPgObjCSVSchema;
import static org.example.readers.CSV.getPojoCsvList;

@Test
public final class CsvReaderTests {

    final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "CSVFiles" + File.separator;

    public void CSVTestOne() throws IOException {

        val loc = PATH_TO_DIR + "test.csv";
        val list = getPojoCsvList(loc, PgObjCSV.class, getPgObjCSVSchema.get());
        Assertions.assertThat(list).isPresent();
        val act = list.get();
        act.forEach(s -> System.out.println(s.getElementName().trim() + "\t" + s.getType().trim() + "\t" +
                s.getLocatorValue().trim()));

        val lOne = act.get(0);
        val lTwo = act.get(1);

        Assert.assertEquals(lOne.getElementName().trim(), "UserName");
        Assert.assertEquals(lOne.getType().trim(), "xpath");
        Assert.assertEquals(lOne.getLocatorValue().trim(), "//h1[@class = 'heello']");

        Assert.assertEquals(lTwo.getElementName().trim(), "Password");
        Assert.assertEquals(lTwo.getType().trim(), "xpath");
        Assert.assertEquals(lTwo.getLocatorValue().trim(), "//a[contains(text(), 'yello')]");
    }

}
