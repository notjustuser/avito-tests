package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.waiters.Waiter;

public class AbsCommon {
    protected WebDriver driver;
    protected Actions action;
    protected Waiter waiter;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.waiter = new Waiter(driver);
    }
}
