package com.saucedemo.automation.tests;

import com.saucedemo.automation.flows.CheckoutFlow;
import com.saucedemo.automation.pages.CheckoutInformationPage;
import com.saucedemo.automation.pages.CheckoutOverviewPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutInformationTest extends AuthenticatedBaseTest {

    @Test
    void validateRequiredCheckoutInformation() {
        String productName = "Sauce Labs Fleece Jacket";

        CheckoutFlow checkoutFlow = new CheckoutFlow(inventoryPage);
        CheckoutInformationPage checkoutInformationPage = checkoutFlow.navigateToInformation(productName);

        assertThat(checkoutInformationPage.getTitleLabel()).isVisible();
        assertThat(checkoutInformationPage.getFirstNameInput()).isVisible();
        assertThat(checkoutInformationPage.getLastNameInput()).isVisible();
        assertThat(checkoutInformationPage.getZipCodeInput()).isVisible();
        assertThat(checkoutInformationPage.getCancelButton()).isVisible();
        assertThat(checkoutInformationPage.getContinueButton()).isVisible();

        checkoutInformationPage.submitEmptyInformation();
        assertThat(checkoutInformationPage.getErrorMessage())
                .containsText("Error: First Name is required");
    }

    @Test
    void validateCheckoutInformationContinuesToOverview() {
        String productName = "Sauce Labs Fleece Jacket";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "07601";

        CheckoutFlow checkoutFlow = new CheckoutFlow(inventoryPage);
        CheckoutInformationPage checkoutInformationPage = checkoutFlow.navigateToInformation(productName);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.submitInformation(firstName,
                lastName, zipCode);

        assertThat(checkoutOverviewPage.getTitleLabel()).isVisible();
        assertThat(checkoutOverviewPage.getPaymentInformationLabel()).isVisible();
        assertThat(checkoutOverviewPage.getShippingInformationLabel()).isVisible();
        assertThat(checkoutOverviewPage.getCancelButton()).isVisible();
        assertThat(checkoutOverviewPage.getFinishButton()).isVisible();
    }
}
