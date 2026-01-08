package t1.core.pages.about;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import t1.core.pages.main.CookiesBanner;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ContactsPage {

    private final CookiesBanner cookiesBanner = new CookiesBanner();

    private final SelenideElement pageTitle =
            $("h1");

    private final SelenideElement phone =
            $("a[href^='tel:']");

    private final SelenideElement email =
            $("a[href^='mailto:']");


    @Step("Страница «Контакты» открыта")
    public void shouldBeOpened() {
        cookiesBanner.waitAndAcceptIfAppears();
        pageTitle.shouldBe(visible);
        webdriver().shouldHave(urlContaining("/contacts"));
    }

    @Step("Контактные данные отображаются")
    public void contactsShouldBeVisible() {
        phone.shouldBe(visible);
        email.shouldBe(visible);
    }

    @Step("Ссылки tel: и mailto: корректны")
    public void linksShouldBeCorrect() {
        phone.shouldHave(attributeMatching("href", "tel:.*"));
        email.shouldHave(attributeMatching("href", "mailto:.*"));
    }

    //@Step("Страница «Контакты» открыта")
//public void shouldBeOpened() {
//    $x("//main | //section").shouldBe(visible);
//}
}

