package mock;


import org.testng.annotations.Factory;
import pojos.DemoPojoOne;
import providers.DemoProviders;

public class MockTestFactoryOne {

    @Factory(dataProviderClass = DemoProviders.class, dataProvider = "DemoDataProviderOne")
    public Object[] getTest(DemoPojoOne demoPojoOne){
        return new Object[]{new MockTestOne(demoPojoOne)};
    }
}
