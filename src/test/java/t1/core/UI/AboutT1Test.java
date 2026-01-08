package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.about.AboutPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.ABOUT_URL;

@Tag("ABOUT")
@DisplayName("About. О компании")
public class AboutT1Test extends BaseTest {

    AboutPage about = new AboutPage();

    @Test
    @Tag("ABOUT-001")
    @DisplayName("ABOUT-001. Страница «О нас» отображается")
    void about001_pageOpened() {

        step("SRZ: Открыть страницу «О нас»", () -> {
            open(ABOUT_URL);
            about.shouldBeOpened();
        });
    }

    @Test
    @Tag("ABOUT-002")
    @DisplayName("ABOUT-002. Кнопка «Скачать презентацию» доступна")
    void about002_presentationButtonVisible() {

        step("SRZ: Открыть страницу «О нас»", () -> {
            open(ABOUT_URL);
            about.shouldBeOpened();
        });

        step("SRZ: Кнопка скачивания презентации отображается", () -> {
            about.presentationButtonShouldBeVisible();
        });
    }
}
