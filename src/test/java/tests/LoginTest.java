package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.ConfigReader;
import utils.TestDataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginTest extends BaseTest {
	
	private static final Logger log = LogManager.getLogger(LoginTest.class);

	 @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = utils.RetryAnalyzer.class, groups = {"regression"})
	 
	 @Feature("Banking Application Login")
	 @Story("User Login with Valid Credentials")
	 @Owner("Shreenu Lingam")
	 @Severity(SeverityLevel.CRITICAL)
	 @Description("Verify that user can login successfully using valid username and password")

	    public void testLogin(String username, String password) {
		 
//		 System.out.println("Running login test with: " + username + " | " + password);
		 log.info("Running login test with user: " + username + ", " + password);
		 
//        getDriver().get("https://demo.guru99.com");
    	getDriver().get(ConfigReader.getProperty("baseURL"));

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href*='V1/index.php']")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

        LoginPage login = new LoginPage(getDriver());
        //login.login("mngr123", "Abcd1234");
        
        //Using Data Provider for multiple logins data.
        login.login(username, password);
        log.info("Login attempt completed for user: " + username + ", " + password);

    }
}