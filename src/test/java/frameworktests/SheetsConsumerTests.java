package frameworktests;

import lombok.val;
import org.example.pojos.MyTestPojoOne;
import org.example.pojos.MyTestPojoThree;
import org.example.pojos.MyTestPojoTwo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.example.annotationconsumer.ConsumeSheetColumn.getXlsxTarget;

@Test
public class SheetsConsumerTests {

      public void testOne() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoOne(2, "fs", "wef", "E"), MyTestPojoOne.class);
        val expectedVal = List.of("fs", "wef", "E");
        val expectedCol = List.of(0, 1, 2);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 2, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
        Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testTwo() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoOne(1232, "fsFV", "weERGf", "EERG"), MyTestPojoOne.class);
        val expectedVal = List.of("fsFV", "weERGf", "EERG");
        val expectedCol = List.of(0, 1, 2);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1232, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testThree() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoOne(23, "fVSDVSs", "wWEFWef", "WEFWEFWEF"), MyTestPojoOne.class);
        val expectedVal = List.of("fVSDVSs", "wWEFWef", "WEFWEFWEF");
        val expectedCol = List.of(0, 1, 2);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 23, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testFour() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoOne(1, "fsdvs", "wsdvef", "AFE"), MyTestPojoOne.class);
        val expectedVal = List.of("fsdvs", "wsdvef", "AFE");
        val expectedCol = List.of(0, 1, 2);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }


      public void testFive() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoTwo(1, "fsdvs", "wsdvef", "AFE"), MyTestPojoTwo.class);
        val expectedVal = List.of("fsdvs", "wsdvef", "AFE");
        val expectedCol = List.of(10, 11, 12);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

      public void testSix() throws IllegalAccessException, InvocationTargetException {
        val res = getXlsxTarget(new MyTestPojoThree(1, "fsdvs", "wsdvef", "AFE"), MyTestPojoThree.class);
        val expectedVal = List.of("fsdvs", "wsdvef", "AFE");
        val expectedCol = List.of(110, 111, 112);
        System.out.println(res.left);
        res.right.forEach((k, v) -> System.out.println(k + "\t" + v));
        Assert.assertEquals(res.left, 1, "[ASSERT FAILED] Row value is a mismatch");

        res.right.forEach((k, v) -> {Assert.assertTrue(expectedCol.contains(k),
                "[ASSERT FAILED column location is a mismatch]");
          Assert.assertTrue(expectedVal.contains(v), "[ASSERT FAILED] Unable to fetch value from getter method");
        });
      }

}
