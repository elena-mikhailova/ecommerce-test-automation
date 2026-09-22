package com.github.elenamikhailova.automation.ui.component;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class Header {


    private final SelenideElement loggedInUser =
            $(".fa-user").closest("a");

    @Step("Verify logged-in user")
    public Header shouldDisplayUser(String userName) {
        loggedInUser
                .shouldBe(visible)
                .shouldHave(text(userName));
        return this;
    }
}
