package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.CollectionCondition.*;

public class ProductsUiTest extends BaseWebTest {

    private final ProductsPage objProductsPage = new ProductsPage();

    @ParameterizedTest(name = "Search term: {0}")
    @DisplayName("User can search terms")
    @MethodSource("com.github.elenamikhailova.automation.api.data.ProductData#searchTerms")
    void canSearchTerms(String searchTerm) {
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        objProductsPage.openPage();
        objProductsPage.searchProduct(searchTerm);
        objProductsPage.getProductNames()
                .shouldHave(sizeGreaterThan(0));
        objProductsPage.getProductNames().shouldHave(anyMatch(
                "at least one product name contains search term",
                element -> element.getText().toLowerCase().contains(lowerCaseSearchTerm)
        ));
    }
}
