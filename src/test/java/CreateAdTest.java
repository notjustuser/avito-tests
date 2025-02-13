import components.popups.CreateAdPopup;
import data.AdContent;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

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
    public void adCreation() {
        MainPage mainPage = new MainPage(driver);
        CreateAdPopup createAdPopup = new CreateAdPopup(driver);
        mainPage.open("/");
        createAdPopup
                .popupShouldNotBeVisible();
        mainPage
                .clickCreateButton()
                .popupShouldBeVisible();
        AdContent adContent = new AdContent(
                "Букет цветов №4",
                "1 200 ₽",
                "Самый красивый букет цветов для вашей дамы",
                "https://static-cdn4-2.vigbo.tech/u71583/83686/blog/5250868/4556660/68384931/1000-4b070b1999dcc7125db82baf0d71d017.jpg");
        mainPage
                .fullInput(adContent)
                .clickSaveButton();
        createAdPopup
                .popupShouldNotBeVisible();

        mainPage.search(adContent.getName())
                .clickFirstAd()
                .fullCheck(adContent);
    }
}

