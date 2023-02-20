package extentsetup;

import org.testng.annotations.Test;

import static org.example.enums.LogType.INFO;
import static org.example.enums.LogType.PASS;
import static org.example.extent.Logger.log;


public class TestTwo {



    @Test
    public void testOne(){
        log(INFO, "iS THIS ENTERTAINGIN");
    }

    @Test(dependsOnMethods = "testOne")
    public void testTwo(){
        log(INFO, "iS THIS ENTERTAINGIN");
        log(INFO, "iS sdsdfsdf rg");
    }

    @Test(dependsOnMethods = "testTwo")
    public void testThree(){
        log(INFO, "iS THIS ENTERTAINGIN");
        log(INFO, "iS THIS wefwef");
    }

    @Test(dependsOnMethods = "testThree")
    public void testFour(){
        log(INFO, "iS THIS ENTERTAINGIN");
        log(PASS, "SKDLVNLSKDNVLSKDNV");
    }




}
