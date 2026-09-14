package com.github.elenamikhailova.automation.data.provider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProductSearchDataProvider {

    public static Stream<Arguments> validSearchCases() {
        return Stream.of(
                Arguments.of(
                        "Search for top", "top"),
                Arguments.of(
                        "Search for tshirt", "tshirt"),
                Arguments.of(
                        "Search for jean", "jean")
        );
    }

    public static Stream<Arguments> invalidSearchCases() {
        return Stream.of(
                Arguments.of(
                        "Search for nonexistent product", "hgytoe")
        );
    }
}
