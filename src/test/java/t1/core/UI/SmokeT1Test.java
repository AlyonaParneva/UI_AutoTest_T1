package t1.core.UI;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.BaseTest;


@ExtendWith(AllureJunit5.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeT1Test extends BaseTest {


    @Test
    @Order(1)
    @Tag("smoke")
    @DisplayName("T1.ru: титул страницы содержит 'T1'")
    void titleContainsT1() {
        step("Открыть главную страницу T1", () -> {
            open("/");
        });
        step("Проверить, что title содержит 'Т1'", () -> {
        Assertions.assertTrue(
                Selenide.title().toUpperCase().contains("Т1")
        );
        });
    }

}
