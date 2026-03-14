package reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
        	
        	String reportDir = System.getProperty("user.dir") + "/target/ExtentReports";
        	new File(reportDir).mkdirs();

//            String reportPath = "target/ExtentReports/ExtentReport.html";
        	
        	 String reportPath = System.getProperty("user.dir") + "/target/ExtentReports/ExtentReport.html";


            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Selenium Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");
            
            // 🔥 Enable Dark Theme
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}