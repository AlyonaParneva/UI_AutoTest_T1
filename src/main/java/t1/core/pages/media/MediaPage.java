package t1.core.pages.media;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import t1.core.pages.main.CookiesBanner;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MediaPage {

    private final CookiesBanner cookiesBanner = new CookiesBanner();

    private final ElementsCollection newsCards =
            $$("a[href^='/media/news/']");

    private final SelenideElement firstNewsCard =
            $("a[href^='/media/news/']");

    @Step("Страница «Медиацентр» открыта")
    public void shouldBeOpened() {
        firstNewsCard.shouldBe(visible);
    }

    @Step("Список новостей отображается")
    public void newsListShouldBeVisible() {
        newsCards.shouldHave(sizeGreaterThan(0));
    }

    @Step("Открыть первую новость")
    public void openFirstNews() {
        cookiesBanner.waitAndAcceptIfAppears();

        firstNewsCard
                .scrollIntoView("{block: 'center'}")
                .shouldBe(visible)
                .click();
    }
}
