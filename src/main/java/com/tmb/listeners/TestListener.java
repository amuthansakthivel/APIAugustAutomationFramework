package com.tmb.listeners;

import com.tmb.annotations.FrameworkAnnotation;
import com.tmb.reports.ExtentLogger;
import com.tmb.reports.ExtentReport;
import com.tmb.utils.JiraUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }
    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.tearDownReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
        //find the author and category and then i need to call that extent report method
        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();

        ExtentReport.addAuthor(authors);

        String[] category = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
        ExtentReport.addCategory(category);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       ExtentLogger.pass(result.getName()+" is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //Awaitility -->
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
       // String issueInJira = JiraUtils.createIssueInJira(String.valueOf(result.getThrowable()));
       // ExtentLogger.fail("Issue created in Jira" + "http://localhost:8080/browse/"+issueInJira);
    }

}
