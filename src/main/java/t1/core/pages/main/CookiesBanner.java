package t1.core.pages.main;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CookiesBanner {

    private final SelenideElement banner =
            $("#notify-container");

    private final SelenideElement okButton =
            $("#notify-container button");

    @Step("Cookies: принять, если баннер отображается")
    public void acceptIfPresent() {

        if (banner.exists() && banner.isDisplayed()) {

            okButton
                    .shouldBe(visible, enabled)
                    .scrollIntoView(true)
                    .click();

            banner.should(disappear);
        }
    }

    @Step("Cookies: баннер не отображается")
    public void shouldNotBeVisible() {
        okButton.shouldNotBe(visible);
    }

    @Step("Cookies: ожидать и закрыть баннер, если он появится")
    public void waitAndAcceptIfAppears() {

        long timeoutMs = Duration.ofMinutes(1).toMillis();
        long pollMs = 1000;
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeoutMs) {

            if (okButton.exists() && okButton.isDisplayed()) {

                okButton
                        .shouldBe(visible, enabled)
                        .click();

                return;
            }

            sleep(pollMs);
        }
    }

}
