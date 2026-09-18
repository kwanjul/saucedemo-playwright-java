package com.saucedemo.automation.tests;

import com.saucedemo.automation.config.TestConfig;
import com.saucedemo.automation.pages.InventoryPage;
import com.saucedemo.automation.pages.LoginPage;
import org.testng.annotations.BeforeMethod;

public class AuthenticatedBaseTest extends BaseTest {

    private final String username = TestConfig.getProperty("test.username");
    protected InventoryPage inventoryPage;

    @BeforeMethod
    protected void logIn() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        String password = TestConfig.getProperty("test.password");
        loginPage.login(username, password);
        inventoryPage = new InventoryPage(page);
    }
}
