package ExtentReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===> Start Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===> End Suite: " + context.getName());
        ExtentManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.saveToReport(result.getMethod().getMethodName(), "Test Description...");
        System.out.println("Start Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.logMessage(Status.PASS, "Test Passed: " + testName);
        System.out.println("===> Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "No error message";
        ExtentTestManager.logMessage(Status.FAIL, "Test Failed: " + testName + "\nReason: " + errorMessage);
        ExtentTestManager.addScreenShot(Status.FAIL, "Failed Screenshot");
        System.out.println("===> Test Failed: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.logMessage(Status.SKIP, "Test Skipped: " + testName);
        System.out.println("===> Test Skipped: " + testName);
    }
}
