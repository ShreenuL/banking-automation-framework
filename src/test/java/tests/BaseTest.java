package tests;

import org.openqa.selenium.Capabilities;

//import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

//import io.github.bonigarcia.wdm.WebDriverManager;

//import reporting.ExtentManager;
//import utils.ConfigReader;
import utils.ScreenshotUtil;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.net.URL;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected ExtentReports extent;
    protected ExtentTest test;

	/*
	 * @BeforeMethod(alwaysRun = true) public void setUp(Method method) {
	 * 
	 * extent = ExtentManager.getInstance(); test =
	 * extent.createTest(method.getName());
	 * 
	 * // ChromeOptions options = new ChromeOptions(); //
	 * options.addArguments("--headless=new"); //
	 * options.addArguments("--no-sandbox"); //
	 * options.addArguments("--disable-dev-shm-usage");
	 * 
	 * ChromeOptions options = new ChromeOptions();
	 * 
	 * if (ConfigReader.getProperty("headless").equals("true")) {
	 * 
	 * options.addArguments("--headless=new");
	 * 
	 * }
	 * 
	 * WebDriverManager.chromedriver().setup();
	 * 
	 * WebDriver browser = new ChromeDriver(options);
	 * 
	 * browser.manage().window().maximize();
	 * 
	 * driver.set(browser); // 🔴 IMPORTANT LINE }
	 */

	/*
	 * @BeforeMethod(alwaysRun = true) public void setup() throws Exception {
	 * 
	 * ChromeOptions options = new ChromeOptions();
	 * options.addArguments("--headless=new"); options.addArguments("--no-sandbox");
	 * options.addArguments("--disable-dev-shm-usage");
	 * 
	 * String gridUrl = System.getenv("GRID_URL");
	 * 
	 * if (gridUrl == null) { gridUrl = "http://localhost:4444"; }
	 * 
	 * URL url = URI.create(gridUrl).toURL();
	 * 
	 * driver.set(new RemoteWebDriver(url, options)); } */

    @BeforeMethod(alwaysRun = true)
    public void setup() throws Exception {

    	String browser = System.getenv("browser");

    	if (browser == null) {
    	    browser = System.getProperty("browser", "chrome");
    	}

        Capabilities options;

        if (browser.equalsIgnoreCase("firefox")) {
            options = new org.openqa.selenium.firefox.FirefoxOptions();
        } else {
            options = new org.openqa.selenium.chrome.ChromeOptions();
            ((org.openqa.selenium.chrome.ChromeOptions) options)
                    .addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }

        String gridUrl = System.getenv("GRID_URL");

        if (gridUrl == null) {
            gridUrl = "http://localhost:4444";
        }

        URL url = URI.create(gridUrl).toURL();

        driver.set(new RemoteWebDriver(url, options));
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        WebDriver driverInstance = getDriver();

        if (result.getStatus() == ITestResult.FAILURE) {

            // Capture screenshot (file)
            String screenshotPath = ScreenshotUtil.captureScreenshot(driverInstance, result.getName());

            // Extent Report
            if (test != null) {
                test.fail(result.getThrowable());

                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {}
            }

            // 🔥 Allure Screenshot (IMPORTANT)
            if (driverInstance != null) {
                ScreenshotUtil.attachScreenshotToAllure(driverInstance);
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            if (test != null) {
                test.pass("Test Passed");
            }
        }

        // Quit driver safely
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();
        }

        // Flush Extent safely
        if (extent != null) {
            extent.flush();
        }
    }
}