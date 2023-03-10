package frameworktests;

import lombok.val;
import org.example.framework.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.example.readers.ApachePOI.*;
import static org.example.writers.ApachePOI.updateCellXlsx;
import static org.example.writers.ApachePOI.updateRowXlsx;


@Test
public class ApachePOITests {

    final static String FILE_PATH = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator + "poiwriter.xlsx";
    final static String FILE_PATH_TWO = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator + "updaterow.xlsx";
    final static String FILE_PATH_THREE = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator + "testpage.xlsx";

    @Test(expectedExceptions = NullPointerException.class)
    public void writeToXlsxZero() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 1, 0, "EA ZERO");
    }

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

    public void writeToXlsxSeven() throws IOException {
        updateCellXlsx(FILE_PATH, "three", 0, 8, "EA NONE");
    }

    public void writeToXlsxFive() throws IOException {
        updateRowXlsx(FILE_PATH_TWO, "one", 0, List.of("A", "B", "C", "D"));
    }

    public void writeToXlsxSix() throws IOException {
        updateRowXlsx(FILE_PATH_TWO, "one", 1, List.of("A", "B"));
    }

    public void readAllFromXlsxTestOne() throws IOException {
        val map  = readAllFromXlsx(FILE_PATH_THREE, "two");
        map.asMap().forEach((k, v) -> System.out.println(k + "\t" + v));
    }

    public void readCellFromXlsxTestOne() throws IOException {
        val res  = readCellFromXlsx(FILE_PATH_THREE, "two", 1, 0);
        System.out.println("Expected -> UserName\nActual -> " + res);
        Assert.assertEquals(res, "UserName");
    }

    public void readRowFromXlsxTestOne() throws IOException {
        val res  = readRowFromXlsx(FILE_PATH_THREE, "two", 1);
        val expected = List.of("UserName", "two", "//h1[@class = 'heello']");
        System.out.println("Expected -> "+expected+"\nActual -> " + res);
        Assert.assertEquals(res, expected);
    }

}
