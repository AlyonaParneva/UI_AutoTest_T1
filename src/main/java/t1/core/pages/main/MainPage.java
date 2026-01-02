package t1.core.pages.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement mainTitle =
            $x("//*[contains(text(),'Бигтех')]");

    private final SelenideElement productsButton =
            $x("//a[contains(@href,'products')]");

    SelenideElement logo =
            $("a[href='/']");

    SelenideElement burgerMenu =
            $("button[aria-label='Меню']");

    ElementsCollection cards =
            $$("section a");

    @Step("Проверка: главная страница T1 открыта")
    public void mainPageIsOpened() {
        mainTitle.shouldBe(visible);
    }

    @Step("Проверка: отображается заголовок 'Бигтех для бизнеса'")
    public void titleShouldBeCorrect() {
        mainTitle.shouldBe(visible);
    }

    @Step("Проверка: отображается кнопка 'Продукты и решения'")
    public void productsButtonShouldBeVisible() {
        productsButton.shouldBe(visible);
    }

    @Step("Клик по кнопке 'Продукты и решения'")
    public void clickOnProductsButton() {
        productsButton.shouldBe(visible).click();
    }

    @Step("Клик по логотипу T1")
    public void clickOnLogo() {
        logo.shouldBe(visible).click();
    }

    @Step("Открытие бургер-меню")
    public void openBurgerMenu() {
        burgerMenu.shouldBe(visible).click();
    }

    @Step("Получить количество карточек на главной странице")
    public int getCardsCount() {
        return cards.size();
    }

    @Step("Заголовок главной страницы отображается")
    public void titleShouldBeVisible() {
        mainTitle.shouldBe(visible);
    }

    @Step("Клик по кнопке «Продукты и решения»")
    public void clickProductsButton() {
        productsButton.shouldBe(visible).click();
    }

    @Step("Клик по логотипу T1")
    public void clickLogo() {
        logo.shouldBe(visible).click();
    }

    @Step("Получить текст заголовка главной страницы")
    public String getTitleText() {
        return mainTitle.shouldBe(visible).getText();
    }
}
