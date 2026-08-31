package com.github.elenamikhailova.automation.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {
    private static final String PRODUCTS_PATH = "/products";

    private final SelenideElement searchInput =
            $("[placeholder='Search Product']");

    private final SelenideElement searchButton =
            $("#submit_search");

    @Getter
    private final ElementsCollection productNames =
            $$(".product-image-wrapper .productinfo p");

    @Step("Enter term")
    public void enterSearchTerm(String searchTerm) {
        searchInput.setValue(searchTerm);
    }

    @Step("Click search button")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Open products page")
    public void openPage() {
        open(PRODUCTS_PATH);
    }

    @Step("Search product: {searchTerm}")
    public void searchProduct(String searchTerm) {
        enterSearchTerm(searchTerm);
        clickSearchButton();
    }
}
