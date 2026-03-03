package pages;

import org.openqa.selenium.WebDriver;

public class HdfcHomePage {

    WebDriver driver;

    public HdfcHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}