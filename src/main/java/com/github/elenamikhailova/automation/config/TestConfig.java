package com.github.elenamikhailova.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    private static final Properties PROPERTIES = new Properties();

    // Load test configuration once and fail fast if it is unavailable
    static {
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Config file not found");
            }
            PROPERTIES.load(input);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load config file", ex);
        }
    }

    private TestConfig() {
    }

    private static String getProperty(String propertyName, String environmentVariable) {
        String systemProperty = System.getProperty(propertyName);

        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        String environmentValue = System.getenv(environmentVariable);

        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }
        return PROPERTIES.getProperty(propertyName);
    }

    public static String getApiBaseUrl() {
        return getProperty("api.base.url", "API_BASE_URL");
    }

    public static String getUiBaseUrl() {
        return getProperty("ui.base.url", "UI_BASE_URL");
    }
}

