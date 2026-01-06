package t1.core.pages.navigation;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HeaderNavigation {

    private final SelenideElement products =
            $x("//a[contains(@href,'/products')]");

    private final SelenideElement cases =
            $x("//a[contains(@href,'/cases')]");

    private final SelenideElement about =
            $x("//a[contains(@href,'/about') and not(contains(@href,'contacts'))]");

    private final SelenideElement contacts =
            $x("//a[contains(@href,'/about/contacts')]");

    SelenideElement logo =
            $("header svg").closest("a");

    @Step("Хедер: пункт «Продукты и решения» видим")
    public void productsShouldBeVisible() {
        products.shouldBe(visible);
    }

    @Step("Хедер: клик «Продукты и решения»")
    public void clickProducts() {
        products.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Хедер: клик «Кейсы»")
    public void clickCases() {
        cases.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Хедер: клик «О нас»")
    public void clickAbout() {
        about.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Хедер: клик «Контакты»")
    public void clickContacts() {
        contacts.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Клик по логотипу T1")
    public void clickLogo() {
        logo
                .shouldBe(visible)
                .scrollIntoView(true)
                .click();
    }

    @Step("Переход на главную страницу")
    public void goToMainPage() {
        open("/");
    }


}