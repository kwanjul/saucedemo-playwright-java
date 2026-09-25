package com.saucedemo.automation.tests;

import com.saucedemo.automation.flows.CheckoutFlow;
import com.saucedemo.automation.pages.CheckoutCompletePage;
import com.saucedemo.automation.pages.CheckoutOverviewPage;
import com.saucedemo.automation.pages.InventoryPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutOverviewTest extends AuthenticatedBaseTest {

    @Test
    void validateCheckoutOverviewPage() {
        String productName = "Sauce Labs Fleece Jacket";
        String productPrice = "$49.99";
        String taxAmount = "$4.00";
        String totalAmount = "$53.99";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "07601";

        CheckoutFlow checkoutFlow = new CheckoutFlow(inventoryPage);
        CheckoutOverviewPage checkoutOverviewPage = checkoutFlow.navigateToOverview(productName,
                firstName, lastName, zipCode);

        assertThat(checkoutOverviewPage.getProductQuantity(productName)).hasText("1");
        assertThat(checkoutOverviewPage.getProductName(productName)).hasText(productName);
        assertThat(checkoutOverviewPage.getProductPrice(productName)).hasText(productPrice);
        assertThat(checkoutOverviewPage.getSubtotalAmount()).hasText("Item total: " + productPrice);
        assertThat(checkoutOverviewPage.getTaxAmount()).hasText("Tax: " + taxAmount);
        assertThat(checkoutOverviewPage.getTotalAmount()).hasText("Total: " + totalAmount);
    }

    @Test
    void validateSuccessfulCheckoutCompletion() {
        String productName = "Sauce Labs Fleece Jacket";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "07601";

        CheckoutFlow checkoutFlow = new CheckoutFlow(inventoryPage);
        CheckoutOverviewPage checkoutOverviewPage = checkoutFlow.navigateToOverview(productName, firstName,
                lastName, zipCode);
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();

        assertThat(checkoutCompletePage.getTitleLabel()).isVisible();
        assertThat(checkoutCompletePage.getConfirmationHeader())
                .hasText("Thank you for your order!");
        assertThat(checkoutCompletePage.getConfirmationText())
                .hasText("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        assertThat(checkoutCompletePage.getBackHomeButton()).isVisible();
    }

    @Test
    void validateBackHomeAfterOrderCompletion() {
        String productName = "Sauce Labs Fleece Jacket";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "07601";

        CheckoutFlow checkoutFlow = new CheckoutFlow(inventoryPage);
        CheckoutOverviewPage checkoutOverviewPage = checkoutFlow.navigateToOverview(productName, firstName,
                lastName, zipCode);
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();
        InventoryPage returnedInventoryPage = checkoutCompletePage.returnToInventory();

        assertThat(returnedInventoryPage.getInventoryList()).isVisible();
        assertThat(returnedInventoryPage.getShoppingCartBadge()).hasCount(0);
    }
}
