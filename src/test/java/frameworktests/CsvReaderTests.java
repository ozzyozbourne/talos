package frameworktests;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.example.framework.Constants;
import org.example.pojos.PgObjCSV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.example.pgobjrepofunc.csv.CSVLoc.getInterpolatedLoc;
import static org.example.readers.CSV.getPojoCsvList;

@Test
public final class CsvReaderTests {

    final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "CSVFiles" + File.separator;

    public void CSVTestOne() throws IOException {

        val loc = PATH_TO_DIR + "test.csv";
        val list = getPojoCsvList(loc, PgObjCSV.class);
        Assertions.assertThat(list).isPresent();
        val act = list.get();
        act.forEach(s -> System.out.println(s.getElementName().trim() + "\t" + s.getType().trim() + "\t" +
                s.getLocatorValue().trim()));

        val lOne = act.get(0);
        val lTwo = act.get(1);
        val lThree = act.get(2);

        Assert.assertEquals(lOne.getElementName().trim(), "UserName");
        Assert.assertEquals(lOne.getType().trim(), "xpath");
        Assert.assertEquals(lOne.getLocatorValue().trim(), "//h1[@class = 'heello']");

        Assert.assertEquals(lTwo.getElementName().trim(), "Password");
        Assert.assertEquals(lTwo.getType().trim(), "xpath");
        Assert.assertEquals(lTwo.getLocatorValue().trim(), "//a[contains(text(), 'yello')]");

        Assert.assertEquals(lThree.getElementName().trim(), "Sign-in");
        Assert.assertEquals(lThree.getType().trim(), "css");
        Assert.assertEquals(lThree.getLocatorValue().trim(), "#singinbutton");

        Assert.assertEquals(act.size(), 3);
    }

    public void csvPageObjRepoTestOne() {
        val pair  = getInterpolatedLoc("testpage", "OnlyCommon", "TUK", "TAAKA");
        Assert.assertEquals(pair.left, "css");
        Assert.assertEquals(pair.right, "TAAKA commons only TUK TUK");
    }

    public void csvPageObjRepoTestTwo() {
        val pair  = getInterpolatedLoc("testpage", "Password");
        Assert.assertEquals(pair.left, "xpath");
        Assert.assertEquals(pair.right, "//a[contains(text(), 'yello')]dev");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void csvPageObjRepoTestException() {
        val pair  = getInterpolatedLoc("testpage", "Password", "one", "two", "three", "four");
    }

    public void csvPageObjRepoTestThree() {
        val pair  = getInterpolatedLoc("testpage", "Four", "ONE", "TWO", "THREE");
        Assert.assertEquals(pair.left, "xpath");
        Assert.assertEquals(pair.right, "THREETHREEONETHREETWOTWOONE");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void csvPageObjRepoTestNoFile() {
        val pair  = getInterpolatedLoc("wrongpage", "Four", "ONE", "TWO", "THREE");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void csvPageObjRepoTestNoLocator() {
        val pair  = getInterpolatedLoc("testpage", "wronglocator", "ONE", "TWO", "THREE");
    }

}
