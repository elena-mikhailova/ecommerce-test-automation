package com.github.elenamikhailova.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement emailInput =
            $("[data-qa='login-email']");

    private final SelenideElement passwordInput =
            $("[data-qa='login-password']");

    private final SelenideElement loginButton =
            $("[data-qa='login-button']");

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

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
}
