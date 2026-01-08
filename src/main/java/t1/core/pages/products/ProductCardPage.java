package t1.core.pages.products;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ProductCardPage {

    private final SelenideElement productTitle =
            $("h1");

    @Step("Карточка продукта открыта")
    public void shouldBeOpened() {
        productTitle.shouldBe(visible);
        webdriver().shouldHave(urlContaining("/products/"));
    }

}
