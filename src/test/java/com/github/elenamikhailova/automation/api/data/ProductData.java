package com.github.elenamikhailova.automation.api.data;

import net.datafaker.Faker;

public class ProductData {
    private final Faker faker = new Faker();

    public String generateSearchTerm() {
        return faker.options().option("top", "tshirt", "jean");
    }
}
