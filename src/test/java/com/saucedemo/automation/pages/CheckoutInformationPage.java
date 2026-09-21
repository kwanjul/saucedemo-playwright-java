package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutInformationPage extends BasePage {

    private final Locator titleLabel;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator zipCodeInput;
    private final Locator continueButton;
    private final Locator cancelButton;
    private final Locator errorMessage;

    public CheckoutInformationPage(Page page) {
        super(page);
        titleLabel = page.getByText("Checkout: Your Information");
        firstNameInput = page.getByPlaceholder("First Name");
        lastNameInput = page.getByPlaceholder("Last Name");
        zipCodeInput = page.getByPlaceholder("Zip/Postal Code");
        continueButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Continue"));
        cancelButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Cancel"));
        errorMessage = page.getByTestId("error");
    }

    public Locator getTitleLabel() {
        return titleLabel;
    }

    public Locator getFirstNameInput() { return firstNameInput; }

    public Locator getLastNameInput() { return lastNameInput; }

    public Locator getZipCodeInput() { return zipCodeInput; }

    public Locator getCancelButton() { return cancelButton; }

    public Locator getContinueButton() { return continueButton; }

    public Locator getErrorMessage() { return errorMessage; }
}
