package frameworktests;

import org.example.framework.Constants;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.example.writers.ApachePOI.updateCellXlsx;


@Test
public class ApachePOITests {

    final static String FILE_PATH = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator + "poiwriter.xlsx";

    public void writeToXlsxOne() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 0, 1, "EA TWO");
    }


}
