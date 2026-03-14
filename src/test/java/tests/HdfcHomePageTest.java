package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ConfigReader;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HdfcHomePageTest extends BaseTest {

    @Test(groups = {"smoke"}, retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyGuru99HomePage() {
    	
    	System.out.println("Thread Name: " + Thread.currentThread().getName());

//        getDriver().get("https://demo.guru99.com");
    	getDriver().get(ConfigReader.getProperty("baseURL"));

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Guru99"));

        String title = getDriver().getTitle();
        System.out.println("Actual Title is: " + title);

        Assert.assertTrue(title.contains("Guru99"), "Title validation failed");
    }
    
    @Test(groups = {"smoke"}, retryAnalyzer = utils.RetryAnalyzer.class)
    public void verifyGuru99Title() {

        System.out.println("Thread Name: " + Thread.currentThread().getName());

        getDriver().get("https://demo.guru99.com");

        String title = getDriver().getTitle();
        Assert.assertTrue(title.contains("Guru99"));
//        Assert.assertTrue(false);
    }
}