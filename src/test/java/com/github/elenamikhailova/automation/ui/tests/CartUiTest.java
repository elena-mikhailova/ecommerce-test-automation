package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.pages.CartPage;
import com.github.elenamikhailova.automation.ui.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class CartUiTest extends BaseWebTest {

    private final ProductsPage objProductsPage = new ProductsPage();
    private final CartPage objCartPage = new CartPage();

    @Test
    @DisplayName("User can add product to cart")
    void canAddProductToCart() {
        objProductsPage.openPage();
        String productName = objProductsPage.addFirstProductToCart();
        objProductsPage.clickViewCartLink();
        objCartPage.getProductNames()
                .findBy(exactText(productName))
                .shouldBe(visible);
    }
}
