package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HdfcHomePageTest extends BaseTest {

    @Test
    public void verifyGuru99HomePage() {

        driver.get("https://demo.guru99.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Guru99"));

        String title = driver.getTitle();
        System.out.println("Actual Title is: " + title);

        Assert.assertTrue(title.contains("Guru99"), "Title validation failed");
    }
}