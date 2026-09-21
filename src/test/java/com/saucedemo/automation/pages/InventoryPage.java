package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.HashMap;
import java.util.Map;

public class InventoryPage extends BasePage {

    private final Locator inventoryList;
    private final Locator sortByDropdown;
    private final Locator productPrices;
    private final Locator shoppingCartLink;
    static final Map<String,String> sortTextValueMap = new HashMap<String,String>();

    static {
        sortTextValueMap.put("Name (A to Z)", "az");
        sortTextValueMap.put("Name (Z to A)", "za");
        sortTextValueMap.put("Price (low to high)", "lohi");
        sortTextValueMap.put("Price (high to low)", "hilo");
    }

    public InventoryPage(Page page) {
        super(page);
        inventoryList = page.getByTestId("inventory-list");
        sortByDropdown = page.getByTestId("product-sort-container");
        productPrices = page.getByTestId("inventory-item-price");
        shoppingCartLink = page.getByTestId("shopping-cart-link");
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

    public Locator getShoppingCartBadge() {
        return page.getByTestId("shopping-cart-badge");
    }

    public Locator getSortByDropdown() {
        return sortByDropdown;
    }

    public Locator getProductPrices() {
        return productPrices;
    }

    public String getDropdownValue(String dropdownText) {
        return sortTextValueMap.get(dropdownText);
    }

    public Locator getShoppingCartLink() {
        return shoppingCartLink;
    }
}
