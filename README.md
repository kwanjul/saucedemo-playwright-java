# Sauce Demo Playwright Java Test Automation

A Playwright and Java test automation framework for the [Sauce Demo](https://www.saucedemo.com/) web application.

The project demonstrates maintainable UI automation using the Page Object Model, TestNG, Playwright locators and assertions, reusable workflow classes, configuration overrides, cross-browser execution, and CSV-driven testing.

## Technology Stack

- Java 17
- Maven
- Playwright for Java
- TestNG

## Test Coverage

The automated suite covers:

- Successful login
- Invalid-password validation
- Inventory-page validation
- Product-name, price, and image validation
- CSV-driven product validation
- Product sorting by price
- Adding products to the cart
- Shopping-cart badge validation
- Cart contents, quantity, and price validation
- Removing products from the cart
- Required checkout-information validation
- Checkout overview and calculated totals
- Successful order completion
- Post-checkout navigation and cart reset
- Logout

## Project Structure

```text
src
└── test
    ├── java
    │   └── com.saucedemo.automation
    │       ├── config
    │       │   └── TestConfig.java
    │       ├── data
    │       │   └── ProductDataProvider.java
    │       ├── flows
    │       │   └── CheckoutFlow.java
    │       ├── pages
    │       │   ├── BasePage.java
    │       │   ├── CartPage.java
    │       │   ├── CheckoutCompletePage.java
    │       │   ├── CheckoutInformationPage.java
    │       │   ├── CheckoutOverviewPage.java
    │       │   ├── InventoryPage.java
    │       │   └── LoginPage.java
    │       └── tests
    │           ├── AuthenticatedBaseTest.java
    │           ├── BaseTest.java
    │           ├── CartTest.java
    │           ├── CheckoutInformationTest.java
    │           ├── CheckoutOverviewTest.java
    │           ├── LoginTest.java
    │           ├── LogoutTest.java
    │           ├── ProductDataDrivenTest.java
    │           └── ProductTest.java
    └── resources
        ├── product-data.csv
        └── test.properties
```

## Framework Design

### Page Object Model

Page classes contain page-specific locators and interaction methods. Tests use these page objects to express workflows without duplicating low-level browser interactions.

Methods that cause navigation return the next page object. For example, proceeding from the cart to checkout returns a `CheckoutInformationPage`.

### Checkout Workflow

`CheckoutFlow` centralizes navigation shared by the checkout tests, including:

- Adding a product to the cart
- Opening the cart
- Proceeding to checkout
- Entering customer information
- Navigating to the checkout overview

Assertions remain in the test classes.

### Browser Lifecycle

`BaseTest` creates:

- One Playwright instance and browser per test class
- A fresh browser context and page for each test invocation

The fresh browser context provides test isolation.

`AuthenticatedBaseTest` extends this setup by logging in before each authenticated test.

### Locator Strategy

The project favors stable and user-facing locators in approximately this order:

1. `getByRole()`
2. `getByLabel()`
3. `getByTestId()`
4. `getByText()`
5. `getByPlaceholder()`
6. `getByAltText()`
7. Simple CSS when necessary
8. XPath as a last resort

Sauce Demo uses the `data-test` attribute instead of Playwright's default `data-testid`. The framework configures this with:

```java
playwright.selectors().setTestIdAttribute("data-test");
```

Product-specific elements are scoped to their product cards using locator chaining and `filter()`.

### Waiting and Assertions

The tests use Playwright's web-first assertions:

```java
assertThat(locator).isVisible();
assertThat(locator).hasText("expected text");
assertThat(locator).containsText("partial text");
assertThat(locator).hasValue("expected value");
assertThat(locator).hasCount(1);
```

These assertions retry until their conditions are satisfied or the configured timeout expires. The framework therefore does not use fixed sleeps.

### Data-Driven Testing

`ProductDataDrivenTest` uses a TestNG `@DataProvider` backed by:

```text
src/test/resources/product-data.csv
```

Each CSV row produces a separate test invocation containing a product name and expected price.

## Prerequisites

Install:

- Java 17 or later
- Maven
- Git

Verify the installations:

```bash
java -version
mvn -version
git --version
```

## Configuration

Default test settings are stored in:

```text
src/test/resources/test.properties
```

Example:

```properties
base.url=https://www.saucedemo.com/
browser=chromium
headless=true
test.username=standard_user
test.password=changeme
```

The committed password is intentionally a placeholder. Supply the test password as a Maven system property when running the suite.

System properties override values in `test.properties`.

## Install Playwright Browsers

Install the Playwright browser binaries:

```bash
mvn exec:java \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install"
```

## Running the Tests

### Default Chromium Run

```bash
mvn clean test -Dtest.password=secret_sauce
```

### Firefox

```bash
mvn clean test \
  -Dtest.password=secret_sauce \
  -Dbrowser=firefox
```

### WebKit

```bash
mvn clean test \
  -Dtest.password=secret_sauce \
  -Dbrowser=webkit
```

### Headed Execution

```bash
mvn clean test \
  -Dtest.password=secret_sauce \
  -Dheadless=false
```

### Combined Overrides

```bash
mvn clean test \
  -Dtest.password=secret_sauce \
  -Dbrowser=firefox \
  -Dheadless=false
```

Supported browser values are:

- `chromium`
- `firefox`
- `webkit`

An unsupported browser value causes the framework to throw an `IllegalArgumentException`.

## Test Results

Maven Surefire test results are generated under:

```text
target/surefire-reports
```

The `target` directory contains generated build artifacts and is excluded from source control.

## Running Tests from IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Allow Maven to load the project dependencies.
3. Open the desired TestNG test class.
4. Select **Run** for the class or an individual test method.
5. Supply `-Dtest.password=secret_sauce` through the run configuration's VM options when required.

## Security Note

Do not commit real credentials to the repository. The password in `test.properties` is intentionally set to a placeholder and should be overridden at runtime.

Sauce Demo is a public demonstration application. Its published credentials should only be used with the Sauce Demo website.
