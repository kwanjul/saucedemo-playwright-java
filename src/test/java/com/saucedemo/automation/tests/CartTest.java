package com.saucedemo.automation.tests;

import com.saucedemo.automation.pages.CartPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartTest extends AuthenticatedBaseTest {

    @Test
    void validateCartPageInteractions() {
        String productName = "Sauce Labs Fleece Jacket";
        String productPrice = "$49.99";

        inventoryPage.getAddToCartButton(productName).click();
        assertThat(inventoryPage.getShoppingCartBadge()).hasText("1");
        inventoryPage.getShoppingCartLink().click();
        CartPage cartPage = new CartPage(page);
        assertThat(cartPage.getContinueShoppingButton()).isVisible();
        assertThat(cartPage.getCheckoutButton()).isVisible();
        assertThat(cartPage.getProductName(productName)).hasText(productName);
        assertThat(cartPage.getProductPrice(productName)).hasText(productPrice);
        assertThat(cartPage.getProductQuantity(productName)).hasText("1");
        cartPage.getRemoveButton(productName).click();
        assertThat(cartPage.getCartItem(productName)).hasCount(0);
        assertThat(cartPage.getShoppingCartBadge()).hasCount(0);
    }
}
