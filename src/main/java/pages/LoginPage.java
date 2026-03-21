package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.name("uid");
    By password = By.name("password");
    By loginBtn = By.name("btnLogin");

    @Step("Enter username: {0}")
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    @Step("Enter password")
    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    @Step("Click login button")
    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    @Step("Login with username: {0}")
    public void login(String user, String pass) {

        enterUsername(user);
        enterPassword(pass);
        clickLogin();

    }
}