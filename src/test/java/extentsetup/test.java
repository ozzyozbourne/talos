package extentsetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.val;
import org.testng.annotations.Test;

public class test {

    @Test
    public void test(){
        // directory where output is to be printed
        val spark = new ExtentSparkReporter("extentsetup/index.html");
        val extent = new ExtentReports();
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("My Report");

    }
}
