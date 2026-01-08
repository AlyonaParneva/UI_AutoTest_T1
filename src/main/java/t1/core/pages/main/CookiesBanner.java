package t1.core.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CookiesBanner {

    private final SelenideElement acceptButton =
            $$("button").findBy(text("Принять"));

    @Step("Cookies: если баннер отображается — принять")
    public void acceptIfPresent() {
        if (acceptButton.exists()) {
            acceptButton.shouldBe(visible).click();
        }
    }

    @Step("Cookies: баннер не отображается")
    public void shouldNotBeVisible() {
        acceptButton.shouldNotBe(visible);
    }
}
