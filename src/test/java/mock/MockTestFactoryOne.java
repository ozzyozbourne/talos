package mock;


import lombok.val;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import pojos.DemoPojoOne;


import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.framework.Constants.PATH_TO_XLSX;
import static org.example.readers.PoijiXl.getPojoFromXlsx;

public class MockTestFactoryOne {

    @Factory(dataProvider = "DemoDataProviderOne")
    public Object[] getTest(DemoPojoOne demoPojoOne){
        return new Object[]{new MockTestOne(demoPojoOne)};
    }

    @DataProvider(name = "DemoDataProviderOne")
    Iterator<Object[]> DemoDataProviderOne(){
        val atomicInt = new AtomicInteger(0);
        val plist = getPojoFromXlsx(PATH_TO_XLSX, "one", DemoPojoOne.class);
        var d2Arr = new Object[plist.size()][1];
        plist.forEach(s -> d2Arr[atomicInt.getAndIncrement()][0] = s);
        return Arrays.stream(d2Arr).iterator();
    }
}
