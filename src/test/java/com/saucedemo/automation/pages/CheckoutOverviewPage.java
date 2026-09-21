package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutOverviewPage extends BasePage {

    private final Locator titleLabel;
    private final Locator paymentInformationLabel;
    private final Locator shippingInformationLabel;
    private final Locator finishButton;
    private final Locator cancelButton;

    public CheckoutOverviewPage(Page page) {
        super(page);
        titleLabel = page.getByText("Checkout: Overview");
        paymentInformationLabel = page.getByText("Payment Information:");
        shippingInformationLabel = page.getByText("Shipping Information:");
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
}
