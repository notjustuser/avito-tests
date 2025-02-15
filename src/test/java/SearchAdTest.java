import factory.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static io.qameta.allure.Allure.step;

public class SearchAdTest {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new WebDriverFactory().create();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Поиск объявлений")
    @Description("Тест на проверку соответствия поисковой выдачи слову в поисковой строке")
    public void adSearching() {
        MainPage mainPage = new MainPage(driver);
        step("Открыть главную страницу", () -> {
            mainPage.open("/");
        });
        String word = "диван";
        step("Ввестив поиск 'Диван', проверить, что во всех названиях есть это слово", () -> {
        mainPage.search(word)
                .changeLimit()
                .titleCheck(word);
         });
    }
}
