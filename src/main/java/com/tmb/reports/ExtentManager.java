package com.tmb.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private ExtentManager(){}

    private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

    public static ExtentTest getTest(){
        return exTest.get();
    }

    static void setExtent(ExtentTest test){
        exTest.set(test);
    }
}
