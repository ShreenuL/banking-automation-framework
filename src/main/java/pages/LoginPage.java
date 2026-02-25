package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By userField = By.name("uid");
    By passField = By.name("password");
    By loginBtn = By.name("btnLogin");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String user, String pass){
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}
