package com.github.elenamikhailova.automation.ui.component;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ConsentPopup {
    private final SelenideElement consentButton =
            $(".fc-cta-consent");

    public void acceptIfVisible() {
        if (consentButton.is(visible, Duration.ofSeconds(5))) {
            consentButton.click();
        }
    }
}
