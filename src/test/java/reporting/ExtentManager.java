package reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // Create report directory inside Jenkins workspace
            String reportDir = System.getProperty("user.dir") + "/target/ExtentReports";
            File dir = new File(reportDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String reportPath = reportDir + "/ExtentReport.html";
            
            System.out.println("Extent Report Path: " + reportPath);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Selenium Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}