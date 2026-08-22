package com.github.elenamikhailova.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {

    private static final Properties PROPERTIES = new Properties();

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

    public static String getApiBaseUrl() {
        return PROPERTIES.getProperty("api.base.url");
    }
}

