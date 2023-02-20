package frameworktests;

import lombok.val;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadAffinityTestNG {

    private long threadId;

    @Factory(dataProvider = "IntValProvider")
    public ThreadAffinityTestNG(int dpVal)  {
        System.out.println(System.getProperty("testng.thread.affinity"));
    }

    @Test(priority = 1)
    public void testOne() throws InterruptedException {
        threadId = Thread.currentThread().getId();
        System.out.println("The thread id -> " + threadId);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testOne")
    public void testTwo() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testTwo")
    public void testThree() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }
    @Test(dependsOnMethods = "testThree")
    public void testFour() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testFour")
    public void testFive() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testFive")
    public void testSix() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testSix")
    public void testSeven() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
        Assert.fail("sdkvnslkdnvklsdv");
    }

    @Test(dependsOnMethods = "testSeven")
    public void testEight() throws InterruptedException {
        Assert.assertEquals(Thread.currentThread().getId(), threadId);
        Thread.sleep(3000);
    }

    @DataProvider(name = "IntValProvider")
    static Iterator<Object[]> IntValProvider(){
        val atomicInt = new AtomicInteger(0);
        val arr = IntStream.range(0, 5).toArray();
        val d2Arr = new Object[arr.length][1];
        Arrays.stream(arr).forEach(s -> d2Arr[atomicInt.getAndIncrement()][0] = s);
        return Arrays.stream(d2Arr).iterator();
    }
}
