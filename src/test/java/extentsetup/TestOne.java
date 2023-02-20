package extentsetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class TestOne {

    private static final String reportPath = "extentreports/index.html";
    private static final String configPath = "src/main/resources/extentconfig/extentconfig.json";
    @Test
    public void test() throws Throwable {

        val spark = new ExtentSparkReporter(reportPath)
                .viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
        val extent = new ExtentReports();
        val CONF = new File(configPath);
        spark.loadJSONConfig(CONF);

        extent.attachReporter(spark);

        val extentTest = extent.createTest("lofing test")
                .assignAuthor("sdkf").assignCategory("smolke").assignDevice("desktop");
        extentTest.pass("SUcessfult");
        extentTest.pass("Url loaded successfullt");
        extentTest.pass(MarkupHelper.createCodeBlock("", CodeLanguage.XML));
        val extentTest1 = extent.createTest("lofing test");
        extentTest1.fail("sdfklskd");
        extentTest1.info("ldkfhlskdv");
        Thread.sleep(10000L);
        val extentTest2 = extent.createTest("lofing test");
        extentTest2.fail(MarkupHelper.createTable(new String[][]{{"afd"}, {"sdvkn"}}));
        extentTest2.fail(MarkupHelper.createLabel("WOho ", ExtentColor.RED));
        extentTest2.fail(MarkupHelper.createOrderedList(List.of("sdvj", "sdfh")));
        extentTest2.fail(MarkupHelper.createUnorderedList(ImmutableMap.of("sdvj", "sdfh", "rfef", "wewf")));
        val re = extentTest2.createNode("new node");
        re.info("sldkfj");
        extent.flush();
            System.gc();
            super.finalize();

        }

    @Test
    public void testNgReprtedtest(){
       val s =  MarkupHelper.createOrderedList(List.of("sdvj", "sdfh")).getMarkup();
        System.out.println(s);
        Reporter.log(s);
    }
}
