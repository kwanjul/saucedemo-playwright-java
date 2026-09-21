package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage extends BasePage {

    private final Locator continueShoppingButton;
    private final Locator checkoutButton;
    private final Locator shoppingCartBadge;

    public CartPage(Page page) {
        super(page);
        continueShoppingButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Continue Shopping"));
        checkoutButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Checkout"));
        shoppingCartBadge = page.getByTestId("shopping-cart-badge");
    }

    public Locator getContinueShoppingButton() {
        return continueShoppingButton;
    }

    public Locator getCheckoutButton() {
        return checkoutButton;
    }

    public Locator getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public Locator getCartItem(String productName) {
        Locator cartItems = page.getByTestId("inventory-item");
        return cartItems.filter(new Locator.FilterOptions().setHasText(productName));
    }

    public Locator getProductName(String productName) {
        return getCartItem(productName).getByTestId("inventory-item-name");
    }

    public Locator getProductQuantity(String productName) {
        return getCartItem(productName).getByTestId("item-quantity");
    }

    public Locator getProductPrice(String productName) {
        return getCartItem(productName).getByTestId("inventory-item-price");
    }

    public Locator getRemoveButton(String productName) {
        return getCartItem(productName).getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Remove"));
    }
}
