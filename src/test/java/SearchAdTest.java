import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

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
    public void adSearching() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open("/");
        String word = "диван";
        mainPage.search(word)
                .changeLimit()
                .titleCheck(word);
    }
}
