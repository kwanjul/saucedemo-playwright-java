package com.saucedemo.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage extends BasePage {

    private final Locator inventoryList;

    public InventoryPage(Page page) {
        super(page);
        inventoryList = page.getByTestId("inventory-list");
    }

    public Locator getInventoryList() {
        return inventoryList;
    }
}