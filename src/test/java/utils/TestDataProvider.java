package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")

    public Object[][] loginData() {

        return new Object[][]{

                {"mngr123", "Abcd1234"},
                {"user1", "password1"},
                {"user2", "password2"}

        };
    }
}