package org.example.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class reporter {

   private reporter(){

    }

    public static ExtentReports extentReports;
   public static ExtentTest test;

   public static void initReports(){
       extentReports = new ExtentReports();
       ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentreport/index.html");
       extentReports.attachReporter(sparkReporter);
   }

   public static void flushReport(){
       extentReports.flush();
   }

   public static void createTest(String s){

   }
}
