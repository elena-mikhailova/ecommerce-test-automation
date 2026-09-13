package com.github.elenamikhailova.automation.tests.ui;

import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.page.CartPage;
import com.github.elenamikhailova.automation.ui.page.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Condition.*;

@Tag("ui")
@Tag("regression")
public class CartUiTest extends BaseWebTest {

    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @Test
    @Tag("smoke")
    @Tag("critical")
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
