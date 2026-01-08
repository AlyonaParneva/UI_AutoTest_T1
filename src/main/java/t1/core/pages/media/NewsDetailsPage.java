package t1.core.pages.media;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class NewsDetailsPage {

    private final SelenideElement articleRoot =
            $("div[class*='article-newsstyles__Box-article-news']");

    private final SelenideElement title =
            articleRoot.$("h1");

    private final SelenideElement date =
            articleRoot.$("div[class*='Tags'] span");

    @Step("Новость открыта")
    public void shouldBeOpened() {

        webdriver().shouldHave(urlContaining("/media/news/"));

        try {
            articleRoot.shouldBe(visible, Duration.ofSeconds(20));
        } catch (Throwable e) {
            refresh();
            articleRoot.shouldBe(visible, Duration.ofMinutes(2));
        }
    }


    @Step("Дата и заголовок новости отображаются")
    public void dateAndTitleShouldBeVisible() {
        title.shouldBe(visible);
        date.shouldBe(visible);
    }
}
