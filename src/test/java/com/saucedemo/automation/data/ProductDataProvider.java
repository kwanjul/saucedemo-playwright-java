package com.saucedemo.automation.data;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ProductDataProvider {

    private static final String CSV_FILE = "product-data.csv";

    private ProductDataProvider() { }

    @DataProvider(name = "productData")
    public static Object[][] productData() {
        InputStream inputStream = ProductDataProvider.class
                .getClassLoader()
                .getResourceAsStream(CSV_FILE);

        if (inputStream == null) {
            throw new IllegalStateException(
                    "CSV test-data file not found: " + CSV_FILE);
        }

        List<Object[]> testData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String header = reader.readLine();

            if (header == null) {
                throw new IllegalStateException(
                        "CSV test-data file is empty: " + CSV_FILE);
            }

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                String[] values = line.split(",", -1);

                if (values.length != 2) {
                    throw new IllegalStateException(
                            "Expected 2 columns on line " + lineNumber
                                    + " of " + CSV_FILE);
                }

                String productName = values[0].trim();
                String expectedPrice = values[1].trim();

                if (productName.isBlank() || expectedPrice.isBlank()) {
                    throw new IllegalStateException(
                            "Missing test data on line " + lineNumber
                                    + " of " + CSV_FILE);
                }

                testData.add(new Object[]{productName, expectedPrice});
            }
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to read CSV test-data file: " + CSV_FILE,
                    exception);
        }

        return testData.toArray(Object[][]::new);
    }
}
