import components.popups.CreateAdPopup;
import data.AdContent;
import factory.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static io.qameta.allure.Allure.step;

public class CreateAdTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new WebDriverFactory().create();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Создание объявления")
    @Description("Тест на проверку создания объявления")
    public void adCreation() {
        MainPage mainPage = new MainPage(driver);
        CreateAdPopup createAdPopup = new CreateAdPopup(driver);
        step("Открыть главную страницу", () -> {
            mainPage.open("/");
        });
        createAdPopup
                .popupShouldNotBeVisible();

        step("Нажать на кнопку 'Создать' ", () -> {
            mainPage
                    .clickCreateButton()
                    .popupShouldBeVisible();
        });
        AdContent adContent = new AdContent(
                "Букет цветов №4",
                "1 200 ₽",
                "Самый красивый букет цветов для вашей дамы",
                "https://static-cdn4-2.vigbo.tech/u71583/83686/blog/5250868/4556660/68384931/1000-4b070b1999dcc7125db82baf0d71d017.jpg");
        step("Заполнить поля и сохранить изменения", () -> {
            mainPage
                    .fullInput(adContent)
                    .clickSaveButton();
        });
        createAdPopup
                .popupShouldNotBeVisible();

        step("Искать объявление по названию и проверить его содержание ", () -> {
            mainPage.search(adContent.getName())
                    .clickFirstAd()
                    .fullCheck(adContent);
        });
    }
}
