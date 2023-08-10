package com.ibm.excerise.listerners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ibm.excerise.testBase.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext) {
        String dateName_number = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myReport_IBM_" + dateName_number + "_.html");//specify location of the report

        htmlReporter.config().setDocumentTitle("IBM Automation Report");
        htmlReporter.config().setReportName("IBM Functional Automation Testing Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "IBM Automation");
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Venkatesh D J");

    }

    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName()); // create new entry in the report

        test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        test.info("************passed ********************");

    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
        try {
            String screenshotPath = BaseTest.getScreenShot(BaseTest.driver, result.getName());
            test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in th report
        test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}
