package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();   // No path
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com");
    }

    @Test
    public void testLogin(){
        LoginPage login = new LoginPage(driver);
        login.login("mngr123", "Abcd1234");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
