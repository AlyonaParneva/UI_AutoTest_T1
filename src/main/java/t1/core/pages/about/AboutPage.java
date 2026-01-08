package t1.core.pages.about;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import t1.core.pages.main.CookiesBanner;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AboutPage {

    private final CookiesBanner cookiesBanner = new CookiesBanner();

    private final SelenideElement title =
            $("h1");

    private final SelenideElement downloadPresentationButton =
            $$("a").findBy(text("Скачать презентацию"));

    @Step("Страница «О нас» открыта")
    public void shouldBeOpened() {
        title.shouldBe(visible);
        webdriver().shouldHave(urlContaining("/about"));
    }

    @Step("Кнопка «Скачать презентацию» отображается")
    public void presentationButtonShouldBeVisible() {
        cookiesBanner.waitAndAcceptIfAppears();
        downloadPresentationButton.shouldBe(visible, enabled);
    }

//    @Step("Страница «О нас» открыта")
//    public void shouldBeOpened() {
//        $x("//main | //section").shouldBe(visible);
//    }
}

