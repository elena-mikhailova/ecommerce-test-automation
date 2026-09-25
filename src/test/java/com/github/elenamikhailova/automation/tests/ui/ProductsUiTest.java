package com.github.elenamikhailova.automation.tests.ui;

import com.github.elenamikhailova.automation.annotation.Regression;
import com.github.elenamikhailova.automation.annotation.Smoke;
import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.page.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class ProductsUiTest extends BaseWebTest {

    private final ProductsPage productsPage = new ProductsPage();

    @ParameterizedTest(name = "Search term: {0}")
    @Smoke
    @Regression
    @DisplayName("User can search products")
    @MethodSource("com.github.elenamikhailova.automation.data.provider.ProductSearchDataProvider#validSearchCases")
    void canSearchProducts(String caseName, String searchTerm) {
        productsPage.openPage()
                .searchProduct(searchTerm)
                .shouldHaveProducts()
                .shouldContainProductMatch(searchTerm);
    }

    @ParameterizedTest(name = "{0}")
    @Regression
    @DisplayName("Search returns no products for nonexistent value")
    @MethodSource("com.github.elenamikhailova.automation.data.provider.ProductSearchDataProvider#invalidSearchCases")
    void returnNoProductsForInvalidSearch(String caseName, String searchTerm) {
        productsPage.openPage()
                .searchProduct(searchTerm)
                .shouldHaveNoProducts();
    }
}
