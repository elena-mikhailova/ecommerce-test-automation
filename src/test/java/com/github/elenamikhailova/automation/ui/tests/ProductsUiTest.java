package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.CollectionCondition.anyMatch;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class ProductsUiTest extends BaseWebTest {

    private final ProductsPage productsPage = new ProductsPage();

    @ParameterizedTest(name = "Search term: {0}")
    @DisplayName("User can search products")
    @MethodSource("com.github.elenamikhailova.automation.data.ProductData#searchTerms")
    void canSearchProducts(String searchTerm) {
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        productsPage.openPage();
        productsPage.searchProduct(searchTerm);
        productsPage.getProductNames()
                .shouldHave(sizeGreaterThan(0));
        productsPage.getProductNames().shouldHave(anyMatch(
                        "at least one product name contains search term",
                        element -> element.getText().toLowerCase().contains(lowerCaseSearchTerm)
                )
        );
    }
}
