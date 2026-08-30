package com.github.elenamikhailova.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private static final String LOGIN_PATH = "/login";

    private final SelenideElement emailInput =
            $("[data-qa='login-email']");

    private final SelenideElement passwordInput =
            $("[data-qa='login-password']");

    private final SelenideElement loginButton =
            $("[data-qa='login-button']");

    @Getter
    private final SelenideElement errorMessage =
            emailInput.closest("form").$("p");

    @Step("Enter email")
    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Login user")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Open login page")
    public void openPage() {
        open(LOGIN_PATH);
    }
}
