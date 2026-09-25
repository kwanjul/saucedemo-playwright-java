package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutCompletePage extends BasePage {

    private final Locator titleLabel;
    private final Locator confirmationHeader;
    private final Locator confirmationText;
    private final Locator backHomeButton;

    public CheckoutCompletePage(Page page) {
        super(page);
        titleLabel = page.getByText("Checkout: Complete!");
        confirmationHeader = page.getByTestId("complete-header");
        confirmationText = page.getByTestId("complete-text");
        backHomeButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Back Home"));
    }

    public Locator getTitleLabel() {
        return titleLabel;
    }

    public Locator getConfirmationHeader() {
        return confirmationHeader;
    }

    public Locator getConfirmationText() {
        return confirmationText;
    }

    public Locator getBackHomeButton() {
        return backHomeButton;
    }

    public InventoryPage returnToInventory() {
        backHomeButton.click();
        return new InventoryPage(page);
    }
}
