package providers;

import lombok.val;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pojos.DemoPojoOne;
import pojos.DemoPojoTwo;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.framework.Constants.PATH_TEST_RC;
import static org.example.readers.PoijiXl.getPojoFromXlsx;

    public class DemoProviders {

    public static final String PATH_TO_XLSX = PATH_TEST_RC + "XLSXFiles" + File.separator + "testpage.xlsx";

    private DemoProviders() {
    }

    @DataProvider(name = "DemoDataProviderOne")
    public static Iterator<Object[]> DemoDataProviderOne(){
        val atomicInt = new AtomicInteger(0);
        val plist = getPojoFromXlsx(PATH_TO_XLSX, "one", DemoPojoOne.class);
        var d2Arr = new Object[plist.size()][1];
        plist.forEach(s -> d2Arr[atomicInt.getAndIncrement()][0] = s);
        return Arrays.stream(d2Arr).iterator();
    }


    @DataProvider(name = "DemoDataProviderTwo")
    public static Iterator<Object[]> DemoDataProviderTwo(){
        val atomicInt = new AtomicInteger(0);
        val plistOne = getPojoFromXlsx(PATH_TO_XLSX, "one", DemoPojoOne.class);
        val plistTwo = getPojoFromXlsx(PATH_TO_XLSX, "three", DemoPojoTwo.class);
        Assert.assertEquals(plistOne.size(), plistTwo.size(), "[ASSERT FAILED] Size Differ");
        var d2Arr = new Object[plistOne.size()][2];
        plistOne.forEach(s ->{d2Arr[atomicInt.get()][0] = s;
            d2Arr[atomicInt.get()][1] = plistTwo.get(atomicInt.getAndIncrement());
        });return Arrays.stream(d2Arr).iterator();
    }
}
