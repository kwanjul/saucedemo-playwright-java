package com.saucedemo.automation.pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    /**
     * Gets the current page URL
     *
     * @return the current URL
     */
    public String getCurrentUrl() {
        return page.url();
    }
}
