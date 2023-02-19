package org.example.extent;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;

import java.util.Arrays;

import static org.example.enums.LogType.*;

public final class Listener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        Reporter.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        Reporter.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if(result.getMethod().getPriority() == 1) {
            Reporter.createTestTag(result.getTestClass().getRealClass().getSimpleName() +" "+result.getMethod().getFactoryMethodParamsInfo().getIndex());
            Logger.logTestTagResult(MarkupHelper.createOrderedList(Arrays.asList(result.getMethod().getFactoryMethodParamsInfo().getParameters())).getMarkup());
            Reporter.createNodeInTestTag(result.getName());
        }else {
            Reporter.createNodeInTestTag(result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logger.logToSpark(PASS,result.getMethod().getMethodName() +" -> [PASSED]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logger.logToSpark(FAIL,result.getMethod().getMethodName() +" -> [FAILED]");
        Logger.logToSpark(FAIL,result.getThrowable().toString());
        Logger.logToSpark(FAIL,MarkupHelper.createUnorderedList(Arrays.asList(result.getThrowable().getStackTrace())).getMarkup());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logger.logToSpark(SKIP,  result.getMethod().getMethodName() + " -> [SKIPPED]");
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
        Logger.logTestTagResult(MarkupHelper.createLabel("Total Ran     -> " + context.getAllTestMethods().length, ExtentColor.TEAL).getMarkup());
        Logger.logTestTagResult(MarkupHelper.createLabel("Total Passed  -> " + context.getPassedTests().size(),    ExtentColor.GREEN).getMarkup());
        Logger.logTestTagResult(MarkupHelper.createLabel("Total Failed  -> " + context.getFailedTests().size(),    ExtentColor.RED).getMarkup());
        Logger.logTestTagResult(MarkupHelper.createLabel("Total Skipped -> " + context.getSkippedTests().size(),   ExtentColor.GREY).getMarkup());
    }


}
