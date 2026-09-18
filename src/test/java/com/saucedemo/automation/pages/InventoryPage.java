package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InventoryPage extends BasePage {

    private final Locator inventoryList;

    public InventoryPage(Page page) {
        super(page);
        inventoryList = page.getByTestId("inventory-list");
    }

    public Locator getInventoryList() {
        return inventoryList;
    }

    public Locator getProductName(String productName) {
        return getProductCard(productName)
                .getByTestId("inventory-item-name");
    }

    public Locator getProductPrice(String productName) {
       return getProductCard(productName).getByTestId("inventory-item-price");
    }

    public Locator getProductImage(String productName) {
        return getProductCard(productName).getByAltText(productName);
    }

    private Locator getProductCard(String productName) {
        return page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }

    public Locator getAddToCartButton(String productName) {
        return getProductCard(productName).getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Add to cart"));
    }

    public Locator getRemoveButton(String productName) {
        return getProductCard(productName).getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Remove"));
    }

    public Locator getCartBadge() {
        return page.getByTestId("shopping-cart-badge");
    }
}
