package ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Framework Name", "Selenium Java Framework");
            extent.setSystemInfo("Project", "OrangeHRM Automation");
            extent.setSystemInfo("Author", "Triệu Hoàng Lòng");
            extent.setSystemInfo("Environment", "Test");
        }
        return extent;
    }
}
