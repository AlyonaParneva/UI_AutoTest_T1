package t1.core.pages.products;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import t1.core.pages.main.CookiesBanner;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {
    private final CookiesBanner cookiesBanner = new CookiesBanner();

    private final SelenideElement pageRoot =
            $("main");

    private final SelenideElement itDirectionAccordion =
            $$("div[data-element='filter-accordion']")
                    .findBy(text("ИТ-направление"));

    private final ElementsCollection itDirectionFilters =
            itDirectionAccordion.$$(
                    "label[data-element='input-label']"
            );

    private final ElementsCollection productCards =
            $$("a[href^='/products/']");

    private final SelenideElement notifyOverlay =
            $("div[class*='notify-content']");

    private final SelenideElement firstProductCard =
            $("a[href^='/products/']");

    @Step("Страница «Продукты и решения» открыта")
    public void shouldBeOpened() {
        firstProductCard.shouldBe(visible);
    }

    @Step("Дождаться исчезновения уведомления")
    public void waitForNotifyToDisappear() {
        if (notifyOverlay.exists()) {
            notifyOverlay.should(disappear);
        }
    }

    @Step("Открыть первую карточку продукта")
    public void openFirstProductCard() {
        firstProductCard.shouldBe(visible);
        cookiesBanner.waitAndAcceptIfAppears();
        executeJavaScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                firstProductCard
        );
        firstProductCard.shouldBe(enabled);
        executeJavaScript(
                "return getComputedStyle(arguments[0]).pointerEvents !== 'none';",
                firstProductCard
        );
        firstProductCard.click();
    }

    @Step("Выбрать первый фильтр ИТ-направления")
    public void selectFirstItFilter() {
        itDirectionFilters
                .shouldHave(sizeGreaterThan(0))
                .first()
                .scrollIntoView(true)
                .click();

        waitForNotifyToDisappear();
    }

    @Step("Выбрать второй фильтр ИТ-направления")
    public void selectSecondItFilter() {
        SelenideElement filter = itDirectionFilters.get(1);

        executeJavaScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                filter
        );

        filter.shouldBe(visible).click();
    }


    @Step("Карточки продуктов отображаются")
    public void productsShouldBeVisible() {
        productCards.shouldBe(sizeGreaterThan(0));
    }

}
