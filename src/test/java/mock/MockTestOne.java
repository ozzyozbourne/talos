package mock;

import net.datafaker.Faker;
import org.testng.annotations.Test;
import pojos.DemoPojoOne;

import java.io.IOException;
import java.util.List;

import static org.example.framework.Constants.PATH_TO_XLSX;
import static org.example.writers.ApachePOI.updateRowXlsx;



public class MockTestOne {

    private final DemoPojoOne demoPojoOne;

    public MockTestOne(DemoPojoOne demoPojoOne) {
        this.demoPojoOne = demoPojoOne;
    }

    @Test(priority = 1)
    public void printDemoPojoObj() throws InterruptedException {
       // Thread.sleep(5000L);
        System.out.println(demoPojoOne);
    }

    @Test(priority = 2)
    public void writeToXlsx() throws IOException, InterruptedException {
       // Thread.sleep(5000L);
        Faker faker = new Faker();
        List<String> names = faker.collection(() -> faker.name().firstName()).len(3, 3).generate();
        System.out.println(demoPojoOne.getRowIndex());
        System.out.println(names);
        updateRowXlsx(PATH_TO_XLSX, "three", demoPojoOne.getRowIndex(), names);
    }

}
