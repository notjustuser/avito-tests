import factory.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static io.qameta.allure.Allure.step;

public class EditAdTest {

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
    @DisplayName("Редактирование объявления")
    @Description("Тест на проверку редактирования объявления и сохранение изменений")
    public void adEditing() {
        MainPage mainPage = new MainPage(driver);
        step("Открыть главную страницу", () -> {
            mainPage.open("/");
        });
        step("Нажать на первое объявление и изменить его название", () -> {
            mainPage.clickFirstAd()
                    .clickEditButton()
                    .input("name", "Носочный монстр")
                    .clickEditButton();
        });
        step("Проверяем, изменилось ли название", () -> {
            mainPage.checkName("Носочный монстр");
        });
    }
}
