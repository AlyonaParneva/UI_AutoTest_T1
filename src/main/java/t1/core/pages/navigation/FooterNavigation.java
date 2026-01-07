package t1.core.pages.navigation;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FooterNavigation {
    private final SelenideElement footer =
            $("footer[componentname='footer']");

    private final SelenideElement footerLogo =
            footer.$("svg[data-icon='t1']").closest("a");

    private final SelenideElement telegram =
            footer.$("#click_telegram");

    private final SelenideElement vk =
            footer.$("#click_vk");

    private final SelenideElement habr =
            footer.$("#click_habr");

    private final SelenideElement vc =
            footer.$("a[id='click_vc.ru']");

    private final SelenideElement phone =
            footer.$("#click_phone");

    private final SelenideElement email =
            footer.$("#click_email");

    private final SelenideElement products =
            $x("//footer//a[@href='/products']");

    private final SelenideElement cases =
            $x("//footer//a[@href='/cases']");

    private final SelenideElement about =
            $x("//footer//a[@href='/about']");

    private final SelenideElement contacts =
            $x("//footer//a[@href='/about/contacts']");

    private final SelenideElement sitemap =
            $x("//footer//a[@href='/sitemap']");

    private final SelenideElement licenseAgreement =
            $x("//footer//a[@href='/license_agreement']");

    private final SelenideElement personalDataPolicy =
            $x("//footer//a[@href='/documents/personal_data_politics']");

    private final SelenideElement itAccreditation =
            $x("//footer//a[@href='/it-accreditation']");


    @Step("Футер отображается")
    public void footerShouldBeVisible() {
        footer.shouldBe(visible);
    }

    @Step("Футер: клик по логотипу T1")
    public void clickFooterLogo() {
        footerLogo.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик Telegram")
    public void clickTelegram() {
        telegram.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик VK")
    public void clickVk() {
        vk.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик Habr")
    public void clickHabr() {
        habr.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик VC.ru")
    public void clickVc() {
        vc.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик по телефону")
    public void clickPhone() {
        phone.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: клик по email")
    public void clickEmail() {
        email.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Все продукты и решения»")
    public void clickProducts() {
        products.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Кейсы»")
    public void clickCases() {
        cases.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «О нас»")
    public void clickAbout() {
        about.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Контакты»")
    public void clickContacts() {
        contacts.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Карта сайта»")
    public void clickSitemap() {
        sitemap.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Пользовательское соглашение»")
    public void clickLicenseAgreement() {
        licenseAgreement.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «Политика персональных данных»")
    public void clickPersonalDataPolicy() {
        personalDataPolicy.shouldBe(visible).scrollIntoView(true).click();
    }

    @Step("Футер: переход «ИТ-аккредитация»")
    public void clickItAccreditation() {
        itAccreditation.shouldBe(visible).scrollIntoView(true).click();
    }
}
