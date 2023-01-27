package mock;

import lombok.val;
import net.datafaker.Faker;
import org.testng.annotations.Test;
import pojos.DemoPojoOne;
import pojos.DemoPojoThree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.example.annotationconsumer.ConsumeSheetColumn.getXlsxTarget;
import static org.example.framework.Constants.PATH_TO_XLSX;
import static org.example.writers.PojoToXlsx.writeRow;


public class MockTestOne {

    private final DemoPojoOne demoPojoOne;
    private final DemoPojoThree demoPojoThree = new DemoPojoThree();
    private final Faker faker = new Faker();

    public MockTestOne(DemoPojoOne demoPojoOne) {
        this.demoPojoOne = demoPojoOne;
    }

    @Test(priority = 1)
    public void printDemoPojoObj() throws InterruptedException {
       // Thread.sleep(5000L);
        System.out.println(demoPojoOne);
        demoPojoThree.setRowNumber(demoPojoOne.getRowIndex());
        demoPojoThree.setEmail(faker.animal().name());
        demoPojoThree.setTown(faker.australia().locations());
    }

    @Test(priority = 2)
    public void addValueToDemoPojoObj() throws InterruptedException {
        // Thread.sleep(5000L);
       demoPojoThree.setLastName(faker.name().lastName());
       demoPojoThree.setFirstName(faker.name().firstName());
       demoPojoThree.setPhoneNumber(faker.phoneNumber().phoneNumber());
       demoPojoThree.setLocation(faker.university().name());
    }

    @Test(priority = 3)
    public void writeToXlsx() throws IOException, InterruptedException, InvocationTargetException, IllegalAccessException {
       // Thread.sleep(5000L);
        System.out.println(demoPojoThree);
        val out = getXlsxTarget(demoPojoThree);
        System.out.println(out.left);
        out.right.forEach((k, v) -> System.out.println(k +"\t"+v));
        writeRow(out, PATH_TO_XLSX, "three");
    }

}
