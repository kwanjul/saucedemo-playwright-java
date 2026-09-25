package com.saucedemo.automation.tests;

import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductTest extends AuthenticatedBaseTest {

    @Test
    void validateSingleProduct() {
        String productName = "Sauce Labs Fleece Jacket";
        String productPrice = "$49.99";
        assertThat(inventoryPage.getInventoryList()).isVisible();
        Locator fleeceJacketName = inventoryPage.getProductName(productName);
        assertThat(fleeceJacketName).hasText(productName);
        Locator fleeceJacketPrice = inventoryPage.getProductPrice(productName);
        assertThat(fleeceJacketPrice).hasText(productPrice);
        Locator fleeceJacketImage = inventoryPage.getProductImage(productName);
        assertThat(fleeceJacketImage).isVisible();
        inventoryPage.addProductToCart(productName);
        Locator removeButton = inventoryPage.getRemoveButton(productName);
        assertThat(removeButton).isVisible();
        Locator cartBadge = inventoryPage.getShoppingCartBadge();
        assertThat(cartBadge).hasText("1");

    }

    @Test
    void validateProductPriceSortLowToHigh() {
        String sortOption = "Price (low to high)";
        inventoryPage.selectSortOption(sortOption);
        assertThat(inventoryPage.getSortByDropdown()).hasValue(inventoryPage.getDropdownValue(sortOption));
        Locator prices = inventoryPage.getProductPrices();
        int count = prices.count();
        Assert.assertTrue(count > 1,
                "Must have at least 2 products to validate price sorting");
        BigDecimal priceOfPreviousItem = new BigDecimal(prices.first().
                innerText().replace("$",""));
        for (int i = 1; i < count; i++) {
            BigDecimal priceOfCurrentItem = new BigDecimal(prices.nth(i).
                    innerText().replace("$",""));
            Assert.assertTrue(priceOfCurrentItem.compareTo(priceOfPreviousItem) >= 0,
                    "Expected " + priceOfCurrentItem + " to be equal to or greater than " +
                            priceOfPreviousItem);
            priceOfPreviousItem = priceOfCurrentItem;
        }
    }
}
