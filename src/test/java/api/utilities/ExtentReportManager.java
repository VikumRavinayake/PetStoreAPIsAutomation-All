package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public Throwable e;
    String reportName;

    public void onStart(ITestContext context){ // before running all test methods
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test Report-PetStore APIs " + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".//reports//" + reportName);
        sparkReporter.config().setDocumentTitle("PetStore Web Services");
        sparkReporter.config().setReportName("Test Report-PetStore APIs");
        sparkReporter.config().setTheme(Theme.DARK);
        

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", "Windows 10 Pro");
        extent.setSystemInfo("Application", "PetStore Web Services v4.0");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Vikum Ravinayake");
        extent.setSystemInfo("User Name", System.getProperty("os.name"));

    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        System.out.println(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode("Test is successful");
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailiure(ITestResult result){
        e = new RuntimeException("A runtime exception");
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode("Something is wrong with the test");
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, e);
          
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode("Not executed");
        test.log(Status.SKIP, "Test Skipped");
          
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
