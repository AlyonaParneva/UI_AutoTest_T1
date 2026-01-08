package t1.core.pages.ar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ArPage {

    private final SelenideElement pageTitle =
            $("h1");

    private final ElementsCollection externalLinks =
            $$("a[target='_blank']");

    private final SelenideElement productsSection =
            $("section");

    @Step("Страница годового отчета открыта")
    public void shouldBeOpened() {
        webdriver().shouldHave(urlContaining("/ar2024"));
        pageTitle.should(exist);
    }


    @Step("Реквизиты и внешние ссылки присутствуют")
    public void requisitesAndLinksShouldBeVisible() {
        externalLinks.shouldHave(sizeGreaterThan(0));
    }

    @Step("Раздел «Продукты, решения и сервисы» отображается")
    public void productsSectionShouldBeVisible() {
        productsSection.shouldBe(visible);
    }
}
