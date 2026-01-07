package t1.core.pages.products;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {

    private final SelenideElement firstProductCard =
            $("a[href^='/products/']");

    @Step("Страница «Продукты и решения» открыта")
    public void shouldBeOpened() {
        firstProductCard.shouldBe(visible);
    }

    @Step("Открыть первую карточку продукта")
    public void openFirstProductCard() {
        firstProductCard.shouldBe(visible);
        executeJavaScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                firstProductCard
        );
        firstProductCard.click();
    }

}
