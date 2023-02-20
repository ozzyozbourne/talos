package org.example.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lombok.val;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Reporter {

    private static ExtentReports extent;

    private Reporter(){}

    static void initReports() {
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            val spark = new ExtentSparkReporter("extentreports/index.html")
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION})
                    .apply();
            try {spark.loadJSONConfig(new File("src/main/resources/extentconfig/extentconfig.json"));
            }catch (IOException e) {
                e.printStackTrace();
                Assert.fail("[ERROR] -> FAILED TO LOAD EXTENT JSON CONFIG FILE");
            }extent.attachReporter(spark);
            System.out.println("Initialised Extent Reporter Successfully");
        }
    }


    static void flushReport(){
        if(Objects.nonNull(extent))
            extent.flush();
    }

    static void createTestTag(String testCaseName){
        Manager.setExtentTest(extent.createTest(testCaseName));
    }

    static void createNodeInTestTag(String testNodeName){
        Manager.setExtentInnerTest(Manager.getExtentTest().createNode(testNodeName)) ;
    }

}
