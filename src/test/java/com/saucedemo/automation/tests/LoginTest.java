package com.saucedemo.automation.tests;

import com.saucedemo.automation.config.TestConfig;
import com.saucedemo.automation.pages.InventoryPage;
import com.saucedemo.automation.pages.LoginPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    private final String username = TestConfig.getProperty("test.username");

    @Test
    void validateSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        String password = TestConfig.getProperty("test.password");
        loginPage.login(username, password);
        InventoryPage inventoryPage = new InventoryPage(page);
        assertThat(inventoryPage.getInventoryList()).isVisible();
    }

    @Test
    void validateInvalidPasswordDisplaysError() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login(username, "bad_password");
        assertThat(loginPage.getErrorMessage()).containsText("Username and password do not match any user in this service");
    }
}
