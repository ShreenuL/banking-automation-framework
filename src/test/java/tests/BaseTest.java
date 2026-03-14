package tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import reporting.ExtentManager;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {

        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");

        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.getProperty("headless").equals("true")) {

            options.addArguments("--headless=new");

        }
        
        WebDriverManager.chromedriver().setup();

        WebDriver browser = new ChromeDriver(options);

        browser.manage().window().maximize();

        driver.set(browser);   // 🔴 IMPORTANT LINE
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(getDriver(), result.getName());

            test.fail(result.getThrowable());

            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {}

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }

        extent.flush();
    }
}