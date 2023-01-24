package mock;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.DemoPojoOne;
import pojos.DemoPojoTwo;

@Test
public class MockTestTwo {

    private final DemoPojoOne demoPojoOne;
    private final DemoPojoTwo demoPojoTwo;

    public MockTestTwo(DemoPojoOne demoPojoOne, DemoPojoTwo demoPojoTwo) {
        this.demoPojoOne = demoPojoOne;
        this.demoPojoTwo = demoPojoTwo;
    }

    public void printBothThePojos() throws InterruptedException {

       // Thread.sleep(5000L);
        System.out.println("[POJO ONE -> ]" +demoPojoTwo.getRowIndex());
        System.out.println("[POJO TWO -> ]" +demoPojoOne.getRowIndex());

        System.out.println(demoPojoOne);
        System.out.println(demoPojoTwo);

        Assert.assertEquals(demoPojoOne.getRowIndex(), demoPojoTwo.getRowIndex(),
                "[ASSERT FAILED] -> Row value differ");
    }

}
