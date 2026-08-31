package com.github.elenamikhailova.automation.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    @Getter
    private final ElementsCollection productNames =
            $$(".cart_description h4 a");

    private final ElementsCollection productRows =
            $$("tr[id^='product-']");

    @Step("Delete product '{productName}' from cart")
    public void deleteProduct(String productName) {
        SelenideElement productRow =
                productRows.findBy(text(productName));

        productRow.$(".cart_quantity_delete").click();
    }
}
