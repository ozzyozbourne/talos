package org.example.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.example.loggers.extent.Reporter;
import org.testng.*;

import java.util.Arrays;

import static org.example.enums.LogType.*;
import static org.example.loggers.extent.Logger.*;
import static org.example.loggers.extent.Logger.logTestTagResult;
import static org.example.loggers.extent.Reporter.flushReport;
import static org.example.loggers.extent.Reporter.initReports;

public final class ExtentListener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if(result.getMethod().getPriority() == 1) {
            Reporter.createTestTag(result.getTestClass().getRealClass().getSimpleName() +" "+result.getMethod().getFactoryMethodParamsInfo().getIndex());
            logTestTagResult(MarkupHelper.createOrderedList(Arrays.asList(result.getMethod().getFactoryMethodParamsInfo().getParameters())).getMarkup());
            Reporter.createNodeInTestTag(result.getName());
        }else {
            Reporter.createNodeInTestTag(result.getName());
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
        Reporter.createTestTag(context.getName() + " Result Summary");
    }

    @Override
    public void onFinish(ITestContext context) {
        logTestTagResult(MarkupHelper.createLabel("Total Ran     -> " + context.getAllTestMethods().length, ExtentColor.TEAL).getMarkup());
        logTestTagResult(MarkupHelper.createLabel("Total Passed  -> " + context.getPassedTests().size(),    ExtentColor.GREEN).getMarkup());
        logTestTagResult(MarkupHelper.createLabel("Total Failed  -> " + context.getFailedTests().size(),    ExtentColor.RED).getMarkup());
        logTestTagResult(MarkupHelper.createLabel("Total Skipped -> " + context.getSkippedTests().size(),   ExtentColor.GREY).getMarkup());
    }


}
