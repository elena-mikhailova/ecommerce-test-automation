package com.github.elenamikhailova.automation.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;


public class Header {

    @Getter
    private final SelenideElement loggedInUser =
            $(".fa-user").closest("a");
}
