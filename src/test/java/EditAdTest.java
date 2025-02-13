import components.popups.CreateAdPopup;
import data.AdContent;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class EditAdTest {

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
    public void adEditing() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open("/");

        mainPage.clickFirstAd()
                .clickEditButton()
                .input("name", "Носочный монстр")
                .clickEditButton();

        mainPage.checkName("Носочный монстр");
    }
}
