package com.github.elenamikhailova.automation.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private static final String VIEW_CART_PATH = "/view_cart";

    @Getter
    private final ElementsCollection productNames =
            $$(".cart_description h4 a");
}
