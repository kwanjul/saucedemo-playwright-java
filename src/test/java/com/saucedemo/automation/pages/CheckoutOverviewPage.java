package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutOverviewPage extends BasePage {

    private final Locator titleLabel;
    private final Locator paymentInformationLabel;
    private final Locator shippingInformationLabel;
    private final Locator subtotalAmount;
    private final Locator taxAmount;
    private final Locator totalAmount;
    private final Locator finishButton;
    private final Locator cancelButton;

    public CheckoutOverviewPage(Page page) {
        super(page);
        titleLabel = page.getByText("Checkout: Overview");
        paymentInformationLabel = page.getByText("Payment Information:");
        shippingInformationLabel = page.getByText("Shipping Information:");
        subtotalAmount = page.getByTestId("subtotal-label");
        taxAmount = page.getByTestId("tax-label");
        totalAmount = page.getByTestId("total-label");
        finishButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Finish"));
        cancelButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Cancel"));
    }

    public Locator getTitleLabel() {
        return titleLabel;
    }

    public Locator getPaymentInformationLabel() {
        return paymentInformationLabel;
    }

    public Locator getShippingInformationLabel() {
        return shippingInformationLabel;
    }

    public Locator getFinishButton() {
        return finishButton;
    }

    public Locator getCancelButton() { return cancelButton; }

    public Locator getProductName(String productName) {
        return getCheckoutItem(productName)
                .getByTestId("inventory-item-name");
    }

    public Locator getProductPrice(String productName) {
        return getCheckoutItem(productName).getByTestId("inventory-item-price");
    }

    public Locator getProductQuantity(String productName) {
        return getCheckoutItem(productName).getByTestId("item-quantity");
    }

    private Locator getCheckoutItem(String productName) {
        return page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }

    public CheckoutCompletePage finishCheckout() {
        finishButton.click();
        return new CheckoutCompletePage(page);
    }

    public Locator getSubtotalAmount() { return subtotalAmount; }

    public Locator getTaxAmount() { return taxAmount; }

    public Locator getTotalAmount() { return totalAmount; }

}
