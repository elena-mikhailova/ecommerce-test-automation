package com.github.elenamikhailova.automation.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    private final SelenideElement loggedInUser =
            $(".fa-user").closest("a");

    public SelenideElement getLoggedInUser() {
        return loggedInUser;
    }
}
