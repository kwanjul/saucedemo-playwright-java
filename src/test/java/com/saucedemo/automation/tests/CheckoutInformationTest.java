package com.saucedemo.automation.tests;

import com.saucedemo.automation.pages.CartPage;
import com.saucedemo.automation.pages.CheckoutInformationPage;
import com.saucedemo.automation.pages.CheckoutOverviewPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutInformationTest extends AuthenticatedBaseTest {

    @Test
    void validateRequiredCheckoutInformation() {
        String productName = "Sauce Labs Fleece Jacket";

        inventoryPage.getAddToCartButton(productName).click();
        inventoryPage.getShoppingCartLink().click();
        CartPage cartPage = new CartPage(page);
        cartPage.getCheckoutButton().click();
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(page);
        assertThat(checkoutInformationPage.getTitleLabel()).isVisible();
        assertThat(checkoutInformationPage.getFirstNameInput()).isVisible();
        assertThat(checkoutInformationPage.getLastNameInput()).isVisible();
        assertThat(checkoutInformationPage.getZipCodeInput()).isVisible();
        assertThat(checkoutInformationPage.getCancelButton()).isVisible();
        assertThat(checkoutInformationPage.getContinueButton()).isVisible();

        checkoutInformationPage.getContinueButton().click();
        assertThat(checkoutInformationPage.getErrorMessage())
                .containsText("Error: First Name is required");
    }

    @Test
    void validateCheckoutInformationPageContinuesToOverview() {
        String productName = "Sauce Labs Fleece Jacket";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "07601";

        inventoryPage.getAddToCartButton(productName).click();
        inventoryPage.getShoppingCartLink().click();
        CartPage cartPage = new CartPage(page);
        cartPage.getCheckoutButton().click();
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(page);
        checkoutInformationPage.getFirstNameInput().fill(firstName);
        checkoutInformationPage.getLastNameInput().fill(lastName);
        checkoutInformationPage.getZipCodeInput().fill(zipCode);
        checkoutInformationPage.getContinueButton().click();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(page);
        assertThat(checkoutOverviewPage.getTitleLabel()).isVisible();
        assertThat(checkoutOverviewPage.getPaymentInformationLabel()).isVisible();
        assertThat(checkoutOverviewPage.getShippingInformationLabel()).isVisible();
        assertThat(checkoutOverviewPage.getCancelButton()).isVisible();
        assertThat(checkoutOverviewPage.getFinishButton()).isVisible();
    }
}
