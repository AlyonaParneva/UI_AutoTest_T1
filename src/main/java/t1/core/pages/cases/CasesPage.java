package t1.core.pages.cases;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import t1.core.pages.main.CookiesBanner;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CasesPage {
    private final CookiesBanner cookiesBanner = new CookiesBanner();

    private final SelenideElement firstCaseCard =
            $("a[href^='/cases/']");

    private final ElementsCollection caseCards =
            $$("a[href^='/cases/']");

    private final SelenideElement industryAccordion =
            $$("div[data-element='filter-accordion']")
                    .findBy(text("Отрасль"));

    private final ElementsCollection industryFilters =
            industryAccordion.$$("label[data-element='input-label']");

    private final SelenideElement notifyOverlay =
            $("div[class*='notify-content']");

    private final SelenideElement spinner =
            $("svg[data-icon='spinner']");

    @Step("Дождаться завершения загрузки (исчезновения спиннера)")
    public void waitForSpinnerToDisappear() {

        long timeoutMs = Duration.ofMinutes(3).toMillis();
        long pollMs = 500;
        long stableMs = 1500;
        long start = System.currentTimeMillis();
        long lastSeenSpinner = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < timeoutMs) {

            boolean spinnerVisible =
                    $$("svg[data-icon='spinner']")
                            .filter(Condition.visible)
                            .size() > 0;

            if (spinnerVisible) {
                lastSeenSpinner = System.currentTimeMillis();
            } else {
                if (System.currentTimeMillis() - lastSeenSpinner > stableMs) {
                    return;
                }
            }

            sleep(pollMs);
        }

        throw new AssertionError("Спиннер не исчез в течение 3 минут");
    }


    @Step("Страница «Кейсы» открыта")
    public void shouldBeOpened() {
        firstCaseCard.shouldBe(visible);
    }

    @Step("Выбрать первый фильтр по отрасли")
    public void selectFirstIndustryFilter() {
        cookiesBanner.waitAndAcceptIfAppears();
        waitForSpinnerToDisappear();
        industryFilters
                .shouldHave(sizeGreaterThan(0))
                .first()
                .scrollIntoView(true)
                .click();

        waitForNotifyToDisappear();
    }

    @Step("Карточки кейсов отображаются")
    public void casesShouldBeVisible() {
        caseCards.shouldBe(sizeGreaterThan(0));
    }

    @Step("Открыть первую карточку кейса")
    public void openFirstCaseCard() {

        firstCaseCard
                .shouldBe(visible)
                .scrollIntoView("{block: 'center'}");

        cookiesBanner.waitAndAcceptIfAppears();
        waitForSpinnerToDisappear();

        firstCaseCard
                .shouldBe(visible, enabled)
                .click();
    }


    @Step("Дождаться исчезновения уведомления")
    public void waitForNotifyToDisappear() {
        if (notifyOverlay.exists()) {
            notifyOverlay.should(disappear);
        }
    }
}