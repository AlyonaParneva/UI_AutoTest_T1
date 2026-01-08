package t1.core.pages.cases;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class CaseDetailsPage {

    private final SelenideElement title =
            $("h1");

    private final SelenideElement content =
            $("section, article, main");

    private final SelenideElement pdfLink =
            $("a[href$='.pdf']");

    @Step("Карточка кейса открыта")
    public void shouldBeOpened() {
        title.shouldBe(visible);
        webdriver().shouldHave(urlContaining("/cases/"));
    }

    @Step("Контент кейса отображается")
    public void contentShouldBeVisible() {
        content.shouldBe(visible);
    }

    @Step("PDF-кейс доступен")
    public void pdfShouldBeAvailable() {
        pdfLink.shouldBe(visible);
    }
}
