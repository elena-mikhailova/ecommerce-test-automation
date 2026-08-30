package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;

public class ProductsUiTest extends BaseWebTest {

    private final ProductsPage objProductsPage = new ProductsPage();


    @Test
    @DisplayName("User can search terms")
    void canSearchTerms() {
        String searchTerm = "top";
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
