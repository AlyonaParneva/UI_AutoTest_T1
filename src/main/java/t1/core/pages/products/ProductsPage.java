package t1.core.pages.products;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {

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

    private void safeScrollAndClick(SelenideElement element) {
        executeJavaScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        element.shouldBe(visible, enabled).click();
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

    @Step("Ожидать кликабельность первой карточки продукта (до 3 минут)")
    public void waitUntilFirstProductCardClickable() {
        WebDriverWait wait = new WebDriverWait(
                webdriver().driver().getWebDriver(),
                Duration.ofMinutes(3)
        );
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.until(driver -> {
            try {
                SelenideElement card = $("a[href^='/products/']");
                card.shouldBe(visible);

                Point location = card.getLocation();
                Dimension size = card.getSize();

                int centerX = location.getX() + size.getWidth() / 2;
                int centerY = location.getY() + size.getHeight() / 2;

                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement elementAtPoint = (WebElement) js.executeScript(
                        "return document.elementFromPoint(arguments[0], arguments[1]);",
                        centerX, centerY
                );

                return elementAtPoint != null && elementAtPoint.equals(card);

            } catch (Exception e) {
                return false;
            }
        });
    }

}
