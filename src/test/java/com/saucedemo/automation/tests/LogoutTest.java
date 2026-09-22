package com.saucedemo.automation.tests;

import com.saucedemo.automation.pages.LoginPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LogoutTest extends AuthenticatedBaseTest {

    @Test
    void successfulLogoutTest() {
        assertThat(inventoryPage.getInventoryList()).isVisible();
        LoginPage loginPage = inventoryPage.logout();
        assertThat(loginPage.getLoginButton()).isVisible();
    }
}
