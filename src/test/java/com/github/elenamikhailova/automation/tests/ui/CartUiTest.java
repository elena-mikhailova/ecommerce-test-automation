package com.github.elenamikhailova.automation.tests.ui;

import com.github.elenamikhailova.automation.annotation.Flaky;
import com.github.elenamikhailova.automation.annotation.Quarantine;
import com.github.elenamikhailova.automation.annotation.Regression;
import com.github.elenamikhailova.automation.annotation.Smoke;
import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.page.CartPage;
import com.github.elenamikhailova.automation.ui.page.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Flaky(
        reason = "Cart flow is unstable because consent popup and add-to-cart modal appear asynchronously"
)
@Quarantine(
        issue = "https://github.com/elena-mikhailova/ecommerce-test-automation/issues/37"
)

public class CartUiTest extends BaseWebTest {

    private final ProductsPage productsPage = new ProductsPage();

    @Test
    @Smoke
    @Regression
    @DisplayName("User can add product to cart")
    void canAddProductToCart() {
        String productName = productsPage
                .openPage()
                .addFirstProductToCart();

        CartPage cartPage = productsPage.clickViewCartLink();
        cartPage.shouldContainProduct(productName);
    }

    @Test
    @DisplayName("User can delete product from cart")
    @Regression
    void canDeleteProductFromCart() {
        String productName = productsPage
                .openPage()
                .addFirstProductToCart();

        CartPage cartPage = productsPage.clickViewCartLink();
        cartPage.shouldContainProduct(productName)
                .deleteProduct(productName)
                .shouldNotContainProduct(productName);
    }
}
