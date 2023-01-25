package frameworktests;

import lombok.val;
import org.example.pojos.MyTestPojo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.annotationconsumer.ConsumeSheetColumn.getXlsxTarget;

@Test
public class SheetsConsumerTests {

      public void testOne() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojo(2, "fs", "wef", "E"), MyTestPojo.class);
        val expectedVal = List.of("fs", "wef", "E");
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 2, "[ASSERT FAILED] Row value is a mismatch");
        val atomicInt = new AtomicInteger(0);
        res.right.forEach((k, v) -> {Assert.assertEquals(k, atomicInt.getAndIncrement(),
                "[ASSERT FAILED column location is a mismatch]");
        Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testTwo() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojo(1232, "fsFV", "weERGf", "EERG"), MyTestPojo.class);
        val expectedVal = List.of("fsFV", "weERGf", "EERG");
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1232, "[ASSERT FAILED] Row value is a mismatch");
        val atomicInt = new AtomicInteger(0);
        res.right.forEach((k, v) -> {Assert.assertEquals(k, atomicInt.getAndIncrement(),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testThree() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojo(23, "fVSDVSs", "wWEFWef", "WEFWEFWEF"), MyTestPojo.class);
        val expectedVal = List.of("fVSDVSs", "wWEFWef", "WEFWEFWEF");
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 23, "[ASSERT FAILED] Row value is a mismatch");
        val atomicInt = new AtomicInteger(0);
        res.right.forEach((k, v) -> {Assert.assertEquals(k, atomicInt.getAndIncrement(),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testFour() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojo(1, "fsdvs", "wsdvef", "AFE"), MyTestPojo.class);
        val expectedVal = List.of("fsdvs", "wsdvef", "AFE");
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1, "[ASSERT FAILED] Row value is a mismatch");
        val atomicInt = new AtomicInteger(0);
        res.right.forEach((k, v) -> {Assert.assertEquals(k, atomicInt.getAndIncrement(),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

}
