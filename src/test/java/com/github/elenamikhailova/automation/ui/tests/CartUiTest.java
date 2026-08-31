package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.pages.CartPage;
import com.github.elenamikhailova.automation.ui.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;

public class CartUiTest extends BaseWebTest {

    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @Test
    @DisplayName("User can add product to cart")
    void canAddProductToCart() {
        productsPage.openPage();
        String productName = productsPage.addFirstProductToCart();
        productsPage.clickViewCartLink();
        cartPage.getProductNames()
                .findBy(exactText(productName))
                .shouldBe(visible);
    }

    @Test
    @DisplayName("User can delete product from cart")
    void canDeleteProductFromCart() {
        productsPage.openPage();
        String productName = productsPage.addFirstProductToCart();
        productsPage.clickViewCartLink();
        cartPage.getProductNames()
                .findBy(exactText(productName))
                .shouldBe(visible);
        cartPage.deleteProduct(productName);
        cartPage.getProductNames()
                .findBy(exactText(productName))
                .shouldNot(exist);
    }
}
