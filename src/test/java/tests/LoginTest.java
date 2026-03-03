package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {

        driver.get("https://demo.guru99.com");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Bank Project link
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bank Project"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
        	    By.cssSelector("a[href*='V1/index.php']")
        	)).click();

        // Wait until username field is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

        LoginPage login = new LoginPage(driver);
        login.login("mngr123", "Abcd1234");
    }
}