package components.popups;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbsBasePage;

public class CreateAdPopup extends AbsBasePage implements IPopup {

    public CreateAdPopup(WebDriver driver) {
        super(driver);
    }

    private By createAdPopupSelector = By.cssSelector("#chakra-modal-\\:rk\\:");

    @Override
    public void popupShouldNotBeVisible() {
        Assertions.assertTrue(
                waiter.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(createAdPopupSelector)),
                "New ad popup should not be visible"
        );
    }

    @Override
    public void popupShouldBeVisible() {
        Assertions.assertTrue(
                waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(createAdPopupSelector)),
                "New ad popup should be visible"
        );
    }
}