package mock;

import org.testng.annotations.Factory;
import pojos.DemoPojoOne;
import pojos.DemoPojoTwo;
import providers.DemoProviders;

public class MockTestFactoryTwo {

    @Factory(dataProviderClass = DemoProviders.class, dataProvider = "DemoDataProviderTwo")
    public Object[] getTest(DemoPojoOne demoPojoOne, DemoPojoTwo demoPojoTwo){
        return new Object[]{new MockTestTwo(demoPojoOne, demoPojoTwo)};
    }
}
