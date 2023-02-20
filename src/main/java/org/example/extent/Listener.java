package org.example.extent;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.val;
import org.testng.*;

import java.util.Arrays;

import static org.example.enums.LogType.*;
import static org.example.extent.Logger.logTestTagResult;
import static org.example.extent.Logger.logToSpark;

public final class Listener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        Reporter.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        Reporter.createTestTag( suite.getName()+ " -> Result Summary");
        for(val e : suite.getResults().entrySet()){
            val heading = MarkupHelper.createLabel("Suite         -> " + e.getKey(), ExtentColor.CYAN).getMarkup();
            val ran     = MarkupHelper.createLabel("Total Ran     -> " + e.getValue().getTestContext().getAllTestMethods().length, ExtentColor.TEAL).getMarkup();
            val passed  = MarkupHelper.createLabel("Total Passed  -> " + e.getValue().getTestContext().getPassedTests().size(), ExtentColor.GREEN).getMarkup();
            val failed  = MarkupHelper.createLabel("Total Failed  -> " + e.getValue().getTestContext().getFailedTests().size(), ExtentColor.RED).getMarkup();
            val skipped = MarkupHelper.createLabel("Total Skipped -> " + e.getValue().getTestContext().getSkippedTests().size(), ExtentColor.GREY).getMarkup();
            logTestTagResult(MarkupHelper.createUnorderedList(Arrays.asList(heading, ran, passed, failed, skipped)).getMarkup());
        }
        Reporter.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if(result.getFactoryParameters().length > 0) {
            if(result.getMethod().getPriority() == 1) {
                Reporter.createTestTag(result.getTestClass().getRealClass().getSimpleName() +" "+result.getMethod().getFactoryMethodParamsInfo().getIndex());
                logTestTagResult(MarkupHelper.createOrderedList(Arrays.asList(result.getMethod().getFactoryMethodParamsInfo().getParameters())).getMarkup());
                Reporter.createNodeInTestTag(result.getName());
            }else Reporter.createNodeInTestTag(result.getName());
        }else {
            if (result.getMethod().getInterceptedPriority() == 0) {
                Reporter.createTestTag(result.getTestClass().getRealClass().getSimpleName());
                Reporter.createNodeInTestTag(result.getName());
            } else Reporter.createNodeInTestTag(result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logToSpark(PASS,result.getMethod().getMethodName() +" -> [PASSED]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logToSpark(FAIL,result.getMethod().getMethodName() +" -> [FAILED]");
        logToSpark(FAIL,result.getThrowable().toString());
        logToSpark(FAIL,MarkupHelper.createUnorderedList(Arrays.asList(result.getThrowable().getStackTrace())).getMarkup());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logToSpark(SKIP,  result.getMethod().getMethodName() + " -> [SKIPPED]");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO document why this method is empty
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO document why this method is empty
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO document why this method is empty
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO document why this method is empty
    }


}
