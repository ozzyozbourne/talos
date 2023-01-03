package sample;

import org.testng.annotations.Test;

@Test
public class SampleTest {

    public void testA(){
        System.out.println("Hello Gradle Testng");
        System.out.println(System.getProperty("tier"));
        System.out.println(System.getProperty("browser"));
        System.out.println(System.getProperty("product"));

    }
}
