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

    public void writeToXlsxTwo() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 0, 2, "EA THREE");
    }
    public void writeToXlsxThree() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 0, 3, "EA FOUR");
    }
    public void writeToXlsxFour() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 0, 4, "EA FIVE");
    }

}
