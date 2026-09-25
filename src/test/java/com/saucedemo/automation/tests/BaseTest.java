package com.saucedemo.automation.tests;

import com.saucedemo.automation.config.TestConfig;
import com.microsoft.playwright.*;
import org.testng.annotations.*;
import java.util.Locale;

/**
 * Base test class that manages Playwright browser lifecycle for all integration tests.
 *
 * Provides setup/teardown of browser instances, contexts, and pages.
 * Subclasses inherit a protected {@code page} instance for use in test methods.
 * Creates one browser per test class and a fresh browser context and page per test method.
 */
public abstract class BaseTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    protected Page page;

    @BeforeClass
    protected void init() {
        playwright = Playwright.create();

        playwright.selectors().setTestIdAttribute("data-test");

        String browserName = TestConfig.getProperty("browser").trim().toLowerCase(Locale.ROOT);

        BrowserType browserType = switch (browserName) {
            case "chromium" -> playwright.chromium();
            case "firefox"  -> playwright.firefox();
            case "webkit"   -> playwright.webkit();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };

        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(TestConfig.getPropertyBoolean("headless")));
    }

    @BeforeMethod
    protected void setUp() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterMethod
    protected void tearDown() {
        if (browserContext != null) browserContext.close();
    }

    @AfterClass
    protected void close() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
