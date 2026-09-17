package com.github.elenamikhailova.automation.ui.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.elenamikhailova.automation.ui.component.ConsentPopup;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {
    private static final String PRODUCTS_PATH = "/products";

    private final ConsentPopup consentPopup = new ConsentPopup();

    private final SelenideElement searchInput =
            $("#search_product");

    private final SelenideElement searchButton =
            $("#submit_search");

    @Getter
    private final ElementsCollection productNames =
            $$(".product-image-wrapper .productinfo p");

    private final ElementsCollection productCards =
            $$(".product-image-wrapper");

    private final SelenideElement viewCartLink =
            $(".modal-body a[href='/view_cart']");

    private final SelenideElement searchedProductsTitle =
            $(".features_items .title");

    @Step("Enter term")
    public ProductsPage enterSearchTerm(String searchTerm) {
        searchInput
                .shouldBe(editable)
                .setValue(searchTerm);
        return this;
    }

    @Step("Click search button")
    public ProductsPage clickSearchButton() {
        searchButton
                .shouldBe(visible)
                .shouldBe(enabled)
                .click();
        return this;
    }

    @Step("Open products page")
    public ProductsPage openPage() {
        open(PRODUCTS_PATH);
        consentPopup.acceptIfVisible();
        return this;
    }

    @Step("Click view cart link")
    public CartPage clickViewCartLink() {
        viewCartLink
                .shouldBe(visible, Duration.ofSeconds(15))
                .click();
        return new CartPage();
    }

    @Step("Add first available product to cart")
    public String addFirstProductToCart() {
        SelenideElement productCard = productCards.first()
                .shouldBe(visible);

        String productName = productCard
                .$(".productinfo p")
                .shouldBe(visible)
                .getText();
        productCard.hover();

        productCard
                .$(".product-overlay .add-to-cart")
                .shouldBe(visible)
                .click();

        return productName;
    }

    @Step("Search product: {searchTerm}")
    public ProductsPage searchProduct(String searchTerm) {
        enterSearchTerm(searchTerm)
                .clickSearchButton();

        searchedProductsTitle
                .shouldBe(visible)
                .shouldHave(exactText("SEARCHED PRODUCTS"));
        return this;
    }
}
