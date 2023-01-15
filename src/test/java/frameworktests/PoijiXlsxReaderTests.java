package frameworktests;

import lombok.val;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.example.framework.Constants;
import org.example.pojos.PgOjbXLSX;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.example.framework.Constants.TIER;
import static org.example.readers.PoijiXl.getPojoFromXlsx;
import static org.example.utils.Interpolation.getLocatorXLSX;

@Test
public final class PoijiXlsxReaderTests {

    final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "XLSXFiles" + File.separator;


    public void PoiJiXLSXReaderTestOne(){
        val loc = PATH_TO_DIR + "testpage.xlsx";
        System.out.println(loc);
        val pojolsit = getPojoFromXlsx(loc, "one", PgOjbXLSX.class);
        System.out.println(pojolsit.size());
        Assert.assertTrue(pojolsit.size() != 0);
        pojolsit.forEach(one -> System.out.println(one.getElementName() + "\t" + one.getType() + "\t" + one.getLocatorValue()));
        Assert.assertEquals(pojolsit.get(3).getLocatorValue(), "${1} commons only ${0} ${0}");
        Assert.assertEquals(pojolsit.get(4).getLocatorValue(), "${2}${2}${0}${2}${1}${1}${0}");

    }

    public void PoiJiXLSXReaderTestTwo(){
        val loc = PATH_TO_DIR + "testpage.xlsx";
        System.out.println(loc);
        val pojolsit = getPojoFromXlsx(loc, "two", PgOjbXLSX.class);
        System.out.println(pojolsit.size());
        Assert.assertTrue(pojolsit.size() != 0);
        pojolsit.forEach(one -> System.out.println(one.getElementName() + "\t" + one.getType() + "\t" + one.getLocatorValue()));
        Assert.assertEquals(pojolsit.get(0).getType(), "two");
    }

    public void PoiJiXLSXReaderTestThree(){
        val loc = PATH_TO_DIR + "testpage.xlsx";
        val pojolsit = getPojoFromXlsx(loc, "two", PgOjbXLSX.class);
        val expList = List.of("UserName", "Password", "Sign-in", "OnlyCommon", "Four");
        pojolsit.forEach(one -> Assert.assertTrue(expList.contains(one.getElementName())));
    }

    public void PoiJiXLSXReaderTestFour(){
        val loc = PATH_TO_DIR + "testpage.xlsx";
        val pojolsit = getPojoFromXlsx(loc, "two", PgOjbXLSX.class);
        val expList = List.of("//h1[@class = 'heello']", "//a[contains(text(), 'yello')]common", "#singinbutton common",
                "${1} commons only ${0} ${0}", "${2}${2}${0}${2}${1}${1}${0}");
        pojolsit.forEach(one -> Assert.assertTrue(expList.contains(one.getLocatorValue())));
    }


    public void PoiJiXLSXReaderTestFive(){
        val loc = PATH_TO_DIR + "testpage.xlsx";
        val pojolsit = getPojoFromXlsx(loc, "two", PgOjbXLSX.class);
        val expList = Collections.singletonList("two");
        pojolsit.forEach(one -> Assert.assertTrue(expList.contains(one.getType())));
    }

    @Test(expectedExceptions = InvalidOperationException.class)
    public void PoiJiXLSXReaderTestSix(){
        val loc = PATH_TO_DIR + "wrongpah.xlsx";
        val pojolsit = getPojoFromXlsx(loc, "two", PgOjbXLSX.class);
    }

    public void xlsxPageObjRepoTestOne(){
        val res = getLocatorXLSX( "testpage", "two", "OnlyCommon", "ONE", "TWO");
        System.out.println(res);
        Assert.assertEquals(res.left, "two");
        Assert.assertEquals(res.right, "TWO commons only ONE ONEdev");

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void xlsxPageObjRepoTestTwo(){
        val res = getLocatorXLSX( "testpage", "two", "OnlyCommons", "ONE", "TWO");
    }

    public void xlsxPageObjRepoTestThree(){
        val res = getLocatorXLSX( "testpage", "two", "Sign-in");
        System.out.println(res);
        Assert.assertEquals(res.left, "two");
        Assert.assertEquals(res.right, "#singinbutton common");

    }

    public void xlsxPageObjRepoTestFour(){
        val res = getLocatorXLSX( "testpage", "two", "Four", "O", "!", "@");
        System.out.println(res);
        Assert.assertEquals(res.left, "two");
        Assert.assertEquals(res.right, "@@O@!!Odev");

    }

}
