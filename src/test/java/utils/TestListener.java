package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener, IAnnotationTransformer {



    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.INFO,"*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.INFO,(result.getMethod().getMethodName() + " failed!"));

        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        String targetLocation = null;

        String testClassName = getTestClassName(result.getInstanceName()).trim();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // get timestamp
        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + timeStamp + ".png";
        String fileSeparator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeparator + "TestReport" + fileSeparator
                + "screenshots";
        ExtentTestManager.getTest().log(Status.INFO,"Screen shots reports path - " + reportsPath);
        try {
            File file = new File(reportsPath + fileSeparator + testClassName); // Set
            // Create screenshots folder
            if (!file.exists()) {
                if (file.mkdirs()) {
                    ExtentTestManager.getTest().log(Status.INFO,"Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    ExtentTestManager.getTest().log(Status.INFO,"Failed to create directory: " + file.getAbsolutePath());
                }
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeparator + testClassName + fileSeparator + screenShotName;// define
            // location
            File targetFile = new File(targetLocation);
            ExtentTestManager.getTest().log(Status.INFO,"Screen shot file location - " + screenshotFile.getAbsolutePath());
            ExtentTestManager.getTest().log(Status.INFO,"Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
            ExtentTestManager.getTest().log(Status.INFO,"File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO,"An exception occurred while taking screenshot " + e.getCause());
        }

        // attach screenshots to report
        try {
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
        } catch (IOException e) {
            ExtentTestManager.getTest().log(Status.INFO,"An exception occured while taking screenshot " + e.getCause());
        }
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        System.out.println("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }


    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }


}
