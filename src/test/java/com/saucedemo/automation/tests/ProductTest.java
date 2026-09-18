package com.saucedemo.automation.tests;

import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductTest extends AuthenticatedBaseTest {

    @Test
    void validateSingleProductTest() {
        String productName = "Sauce Labs Fleece Jacket";
        String productPrice = "$49.99";
        assertThat(inventoryPage.getInventoryList()).isVisible();
        Locator fleeceJacketName = inventoryPage.getProductName(productName);
        assertThat(fleeceJacketName).hasText(productName);
        Locator fleeceJacketPrice = inventoryPage.getProductPrice(productName);
        assertThat(fleeceJacketPrice).hasText(productPrice);
        Locator fleeceJacketImage = inventoryPage.getProductImage(productName);
        assertThat(fleeceJacketImage).isVisible();
        inventoryPage.getAddToCartButton(productName).click();
        Locator removeButton = inventoryPage.getRemoveButton(productName);
        assertThat(removeButton).isVisible();
        Locator cartBadge = inventoryPage.getCartBadge();
        assertThat(cartBadge).hasText("1");
    }
}
