package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.products.ProductCardPage;
import t1.core.pages.products.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("PRD")
@DisplayName("Products. Каталог решений")
public class ProductsT1Test extends BaseTest {

    ProductsPage products = new ProductsPage();
    ProductCardPage productCard = new ProductCardPage();

    @Test
    @Tag("PRD-001")
    @DisplayName("PRD-001. Каталог продуктов отображается")
    void prd001_productsCatalogVisible() {

        step("SRZ: Открыть страницу продуктов", () -> {
            open("/products");
            products.shouldBeOpened();
        });

        step("SRZ: Отображаются карточки продуктов", () -> {
            products.productsShouldBeVisible();
        });
    }

    @Test
    @Tag("PRD-002")
    @DisplayName("PRD-002. Фильтрация по ИТ-направлению")
    void prd002_itFiltering() {

        step("SRZ: Открыть страницу продуктов", () -> {
            open("/products");
            products.shouldBeOpened();
        });

        step("SRZ: Применить фильтр по ИТ-направлению", () -> {
            products.selectFirstItFilter();
        });

        step("SRZ: Отображаются отфильтрованные продукты", () -> {
            products.productsShouldBeVisible();
        });
    }

    @Test
    @Tag("PRD-003")
    @DisplayName("PRD-003. Комбинированная фильтрация")
    void prd003_combinedFiltering() {

        step("SRZ: Открыть страницу продуктов", () -> {
            open("/products");
            products.shouldBeOpened();
        });

        step("SRZ: Применить несколько фильтров", () -> {
            products.selectFirstItFilter();
            products.selectSecondItFilter();
        });

        step("SRZ: Отображаются карточки продуктов", () -> {
            products.productsShouldBeVisible();
        });
    }

    @Test
    @Tag("PRD-004")
    @DisplayName("PRD-004. Открытие карточки продукта")
    void prd004_openProductCard() {

        step("SRZ: Открыть страницу продуктов", () -> {
            open("/products");
            products.shouldBeOpened();
        });

        step("SRZ: Открыть первую карточку продукта", () -> {
            products.openFirstProductCard();
        });

        step("SRZ: Карточка продукта открыта", () -> {
            productCard.shouldBeOpened();
        });
    }
}
