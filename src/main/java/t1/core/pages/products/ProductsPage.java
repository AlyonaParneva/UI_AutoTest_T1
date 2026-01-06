package t1.core.pages.products;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage {
    @Step("Страница «Продукты и решения» открыта")
    public void shouldBeOpened() {
        $x("//main | //section").shouldBe(visible);
    }
}
