package mock;

import lombok.val;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import pojos.DemoPojoOne;
import pojos.DemoPojoTwo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.framework.Constants.PATH_TO_XLSX;
import static org.example.readers.PoijiXl.getPojoFromXlsx;


public class MockTestFactoryTwo {

    @Factory(dataProvider = "DemoDataProviderTwo")
    public Object[] getTest(DemoPojoOne demoPojoOne, DemoPojoTwo demoPojoTwo){
        return new Object[]{new MockTestTwo(demoPojoOne, demoPojoTwo)};
    }

    @DataProvider(name = "DemoDataProviderTwo")
     Iterator<Object[]> DemoDataProviderTwo(){
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
