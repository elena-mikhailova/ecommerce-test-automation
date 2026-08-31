package com.github.elenamikhailova.automation.data;

import java.util.stream.Stream;

public class ProductData {

    public static Stream<String> searchTerms() {
        return Stream.of(
                "top",
                "tshirt",
                "jean"
        );
    }
}
