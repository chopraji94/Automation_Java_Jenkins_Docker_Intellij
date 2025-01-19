package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReport;
    public ExtentReports extentReports;
    public ExtentTest test;
    public String repname;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repname = "Test-Report"+timeStamp+".html";
        sparkReport = new ExtentSparkReporter(System.getProperty("user.dir")+".\\reports\\"+repname);

        sparkReport.config().setDocumentTitle("Extent report of tests");
        sparkReport.config().setReportName("Functional testing");
        sparkReport.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReport);

        extentReports.setSystemInfo("Application", "LoginCheck");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub module", "Customer");
        extentReports.setSystemInfo("User name", "admin");
        extentReports.setSystemInfo("Envioment", "QA");
    }

    public void onTestSuccess(ITestResult result) {
        test = extentReports.createTest(result.getTestClass().getName());
        test.log(Status.PASS,result.getName()+" got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extentReports.createTest(result.getTestClass().getName());
        test.log(Status.FAIL,result.getName()+" got failed");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = extentReports.createTest(result.getTestClass().getName());
        test.log(Status.SKIP,result.getName()+" got skipped");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
