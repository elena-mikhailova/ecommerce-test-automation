package com.github.elenamikhailova.automation.ui.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private final ElementsCollection productNames =
            $$(".cart_description h4 a");

    private final ElementsCollection productRows =
            $$("tr[id^='product-']");

    @Step("Delete product '{productName}' from cart")
    public CartPage deleteProduct(String productName) {
        SelenideElement productRow =
                productRows.findBy(text(productName));

        productRow.$(".cart_quantity_delete").click();
        return this;
    }

    @Step("Verify product '{productName}' in cart")
    public CartPage shouldContainProduct(String productName) {
        productNames
                .findBy(exactText(productName))
                .shouldBe(visible);
        return this;
    }

    @Step("Verify product '{productName}' from cart")
    public CartPage shouldNotContainProduct(String productName) {
        productNames
                .findBy(exactText(productName))
                .shouldNot(exist);
        return this;
    }
}
