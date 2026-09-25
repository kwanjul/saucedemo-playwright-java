package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import java.util.Map;

public class InventoryPage extends BasePage {

    private final Locator inventoryList;
    private final Locator sortByDropdown;
    private final Locator productPrices;
    private final Locator shoppingCartLink;
    private final Locator openMenuButton;
    private final Locator logoutButton;

    private static final Map<String, String> SORT_TEXT_VALUE_MAP = Map.of(
            "Name (A to Z)", "az",
            "Name (Z to A)", "za",
            "Price (low to high)", "lohi",
            "Price (high to low)", "hilo"
    );

    public InventoryPage(Page page) {
        super(page);
        inventoryList = page.getByTestId("inventory-list");
        sortByDropdown = page.getByTestId("product-sort-container");
        productPrices = page.getByTestId("inventory-item-price");
        shoppingCartLink = page.getByTestId("shopping-cart-link");
        openMenuButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Open Menu"));
        logoutButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Logout"));
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

    public void addProductToCart(String productName) {
        getProductCard(productName).getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Add to cart")).click();
    }

    public Locator getRemoveButton(String productName) {
        return getProductCard(productName).getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Remove"));
    }

    public Locator getShoppingCartBadge() {
        return page.getByTestId("shopping-cart-badge");
    }

    public void selectSortOption(String optionLabel) {
        sortByDropdown.selectOption(new SelectOption().setLabel(optionLabel));
    }

    public Locator getSortByDropdown() {
        return sortByDropdown;
    }

    public Locator getProductPrices() {
        return productPrices;
    }

    public String getDropdownValue(String dropdownText) {
        String value = SORT_TEXT_VALUE_MAP.get(dropdownText);
        if (value == null) {
            throw new IllegalArgumentException("Unknown dropdown option: " + dropdownText);
        }
        return value;
    }

    public CartPage openCart() {
        shoppingCartLink.click();
        return new CartPage(page);
    }

    public LoginPage logout() {
        openMenuButton.click();
        logoutButton.click();
        return new LoginPage(page);
    }
}
