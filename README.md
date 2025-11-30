# UI Autotest Skeleton (Selenide + JUnit 5 + Allure)

Готовый каркас для фронт-тестов сайта **https://t1.ru**.

## Быстрый старт (локально)
```bash
mvn -DbaseUrl=https://t1.ru -Dheadless=true test
mvn allure:report && echo 'open target/site/allure-maven-plugin/index.html'
```

## Запуск против Selenium в Docker
```bash
docker compose up --abort-on-container-exit
```
Переменные можно передать как -D: `baseUrl`, `browser`, `headless`, `remoteUrl`.

## Структура
- `tests/ui/SmokeT1Test.java` — пример смоук-теста тайтла.
- `listeners/AllureSelenideSetup.java` — скриншоты/page source в Allure.
- `config/TestConfig.java` — базовые настройки Selenide.
- `drivers/DriverFactory.java` — установка драйвера локально.
- `resources/config.properties` — значения по умолчанию.

