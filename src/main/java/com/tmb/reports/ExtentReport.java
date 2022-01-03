package com.tmb.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public final class ExtentReport {

    private  ExtentReport(){}
    private static ExtentReports extent;
    private static ExtentTest test;  //thread safe --> ThreadLocal


    public static void initReports(){
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
        extent.attachReporter(spark);

    }

    public static void tearDownReports(){
        extent.flush();
    }

    public static void createTest(String name) {
        test = extent.createTest(name);
        ExtentManager.setExtent(test);
    }

    public static void addAuthor(String[] authors){
        for(String author:authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }

    public static void addCategory(String[] categories){
        for(String category:categories) {
            ExtentManager.getTest().assignCategory(category);
        }
    }
}
