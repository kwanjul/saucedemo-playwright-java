package com.saucedemo.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final String CONFIG_FILE = "test.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Configuration file not found: " + CONFIG_FILE);
            }
            PROPERTIES.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load configuration file: " + CONFIG_FILE, exception);
        }
    }

    private TestConfig() { }

    public static String getProperty(String property) {
        String value = System.getProperty(property, PROPERTIES.getProperty(property));

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Configuration property is missing: " + property);
        }
        return value;
    }

    public static boolean getPropertyBoolean(String property) {
        String value = getProperty(property).trim();
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException("Configuration property must be true or false: "
                    + property + "=" + value);
        }
        return Boolean.parseBoolean(value);
    }
}
