package org.example.loggers.extent;

import com.aventstack.extentreports.ExtentTest;

public final class Manager {

    private Manager() {
    }

    private static final ThreadLocal<ExtentTest> extOuter = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> extInner = new ThreadLocal<>();

    static ExtentTest getExtentTest(){
        return extOuter.get();
    }

    static void setExtentTest(ExtentTest test){
        extOuter.set(test);
    }
    static void unloadOuter(){
         extOuter.remove();
    }


    static ExtentTest getExtentInnerTest(){
        return extInner.get();
    }

    static void setExtentInnerTest(ExtentTest test){
        extInner.set(test);
    }
    static void unloadInner(){
        extInner.remove();
    }
}
