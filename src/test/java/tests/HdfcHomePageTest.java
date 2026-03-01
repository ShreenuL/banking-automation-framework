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
import pages.HdfcHomePage;
import org.openqa.selenium.chrome.ChromeOptions;

public class HdfcHomePageTest {

    @Test
    public void verifyHdfcBankHomePage() {

//        WebDriver driver = new ChromeDriver();s
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.hdfc.bank.in/");

        // Create object of Page Class
        HdfcHomePage homePage = new HdfcHomePage(driver);

        // Validate title using Page method
		/*
		 * Assert.assertTrue(homePage.getPageTitle().contains("HDFC"),
		 * "Title does not contain HDFC");
		 */
        
        Assert.assertTrue(homePage.getPageTitle().contains("HDFC"));
        
        String title = homePage.getPageTitle();
        System.out.println("Page Title is: " + title);
        Assert.assertTrue(title.contains("HDFC"), "Title validation failed");
        
        driver.quit();
    }
}