package com.saucedemo.automation.flows;

import com.saucedemo.automation.pages.CartPage;
import com.saucedemo.automation.pages.CheckoutInformationPage;
import com.saucedemo.automation.pages.CheckoutOverviewPage;
import com.saucedemo.automation.pages.InventoryPage;

public class CheckoutFlow {
    private final InventoryPage inventoryPage;

    public CheckoutFlow(InventoryPage inventoryPage){
        this.inventoryPage = inventoryPage;
    }

    public CheckoutInformationPage navigateToInformation(String productName) {
        inventoryPage.addProductToCart(productName);
        CartPage cartPage = inventoryPage.openCart();
        return cartPage.proceedToCheckout();
    }

    public CheckoutOverviewPage navigateToOverview(String productName, String firstName,
            String lastName, String zipCode) {
        CheckoutInformationPage checkoutInformationPage = navigateToInformation(productName);
        return checkoutInformationPage.submitInformation(firstName, lastName, zipCode);
    }
}
