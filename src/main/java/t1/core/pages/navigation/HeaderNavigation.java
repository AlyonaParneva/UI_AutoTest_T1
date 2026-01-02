package t1.core.pages.navigation;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HeaderNavigation {

    SelenideElement productsLink = $("a[href='/products']");
    SelenideElement casesLink = $("a[href='/cases']");
    SelenideElement aboutLink = $("a[href='/about']");
    SelenideElement contactsLink = $("a[href='/about/contacts']");
    SelenideElement pageTitle = $("h1");

    @Step("Переход в раздел «Продукты и решения»")
    public void goToProducts() {
        productsLink.shouldBe(visible).click();
    }

    @Step("Переход в раздел «Кейсы»")
    public void goToCases() {
        casesLink.shouldBe(visible).click();
    }

    @Step("Переход в раздел «О компании»")
    public void goToAbout() {
        aboutLink.shouldBe(visible).click();
    }

    @Step("Переход в раздел «Контакты»")
    public void goToContacts() {
        contactsLink.shouldBe(visible).click();
    }

    @Step("Проверка отображения заголовка страницы")
    public void pageTitleShouldBeVisible() {
        pageTitle.shouldBe(visible);
    }
}
