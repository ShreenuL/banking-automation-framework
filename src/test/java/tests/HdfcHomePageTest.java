/*
 * package tests;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import org.testng.Assert; import
 * org.testng.annotations.Test;
 * 
 * public class HdfcHomePageTest {
 * 
 * @Test public void verifyHdfcBankHomePage() {
 * 
 * WebDriver driver = new ChromeDriver(); driver.manage().window().maximize();
 * 
 * // Launch HDFC Bank website driver.get("https://www.hdfc.bank.in/");
 * 
 * // 1. Verify page title String title = driver.getTitle();
 * Assert.assertTrue(title.contains("HDFC Bank"),
 * "Page title does not contain HDFC Bank");
 * 
 * // 2. Verify NetBanking link is present
 * 
 * boolean netBankingVisible =
 * driver.findElement(By.linkText("NetBanking")).isDisplayed();
 * 
 * Assert.assertTrue(netBankingVisible, "NetBanking link is not visible");
 * 
 * 
 * System.out.println("HDFC Bank home page verified successfully");
 * 
 * driver.quit(); } }
 */

package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HdfcHomePage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HdfcHomePageTest {

    @Test
    public void verifyHdfcBankHomePage() {

//        WebDriver driver = new ChromeDriver();s
        
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless=new");
    	options.addArguments("--no-sandbox");
    	options.addArguments("--disable-dev-shm-usage");

    	WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver(options);

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	driver.get("https://www.hdfc.bank.in/");

    	// Wait until title contains HDFC
    	wait.until(ExpectedConditions.titleContains("HDFC"));

    	String title = driver.getTitle();
    	System.out.println("Title is: " + title);

    	Assert.assertTrue(title.contains("HDFC"), "Title validation failed");
    	System.out.println("Title is: " + driver.getTitle());

    	driver.quit();
    }
}