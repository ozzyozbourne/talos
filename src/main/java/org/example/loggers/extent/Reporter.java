package org.example.loggers.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lombok.val;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.example.loggers.extent.Manager.getExtentTest;
import static org.example.loggers.extent.Manager.setExtentInnerTest;

public final class Reporter {

    private static final String reportPath = "extentreports/index.html";
    private static final String configPath = "src/main/resources/extentconfig/extentconfig.json";
    private static ExtentReports extent;

    public static void initReports() {
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            val spark = new ExtentSparkReporter(reportPath)
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION})
                    .apply();
            try {spark.loadJSONConfig(new File(configPath));
            }catch (IOException e) {
                e.printStackTrace();
                Assert.fail("[ERROR] -> FAILED TO LOAD EXTENT JSON CONFIG FILE");
            }extent.attachReporter(spark);
            System.out.println("Initialised Extent Reporter Successfully");
        }
    }

    private Reporter(){}


    public static void flushReport(){
        if(Objects.nonNull(extent))
            extent.flush();
    }

    public static void createTestTag(String testCaseName){
        Manager.setExtentTest(extent.createTest(testCaseName));
    }

    public static void createNodeInTestTag(String testNodeName){
        setExtentInnerTest(getExtentTest().createNode(testNodeName)) ;
    }

}
