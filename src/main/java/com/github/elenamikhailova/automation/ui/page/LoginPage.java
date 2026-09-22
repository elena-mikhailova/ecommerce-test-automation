package com.github.elenamikhailova.automation.ui.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
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

    private final SelenideElement errorMessage =
            emailInput.closest("form").$("p");

    @Step("Verify login error")
    public LoginPage shouldHaveError(String expectedError) {
        errorMessage
                .shouldBe(visible)
                .shouldHave(exactText(expectedError));
        return this;
    }

    @Step("Enter email")
    public LoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Login user")
    public void login(String email, String password) {
        enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();
    }

    @Step("Open login page")
    public LoginPage openPage() {
        open(LOGIN_PATH);
        return this;
    }
}
