package frameworktests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.example.framework.Constants;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.example.writers.ApachePOI.writeToXlsx;

@Test
public class ApachePOITests {

    final static String FILE_PATH = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator + "poiwriter.xlsx";

    public void writeToXlsxOne() throws IOException, InvalidFormatException {
        writeToXlsx(FILE_PATH, "three", 0, 0, "EA ONE");
    }


}
