package com.saucedemo.automation.tests;

import com.saucedemo.automation.data.ProductDataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductDataDrivenTest extends AuthenticatedBaseTest {

    @Test(
            dataProvider = "productData",
            dataProviderClass = ProductDataProvider.class
    )
    void validateProductData(String productName, String expectedPrice) {
        assertThat(inventoryPage.getProductName(productName))
                .hasText(productName);

        assertThat(inventoryPage.getProductPrice(productName))
                .hasText(expectedPrice);

        assertThat(inventoryPage.getProductImage(productName))
                .isVisible();
    }
}
