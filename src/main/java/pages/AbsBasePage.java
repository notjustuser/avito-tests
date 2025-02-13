package pages;

import org.openqa.selenium.WebDriver;
import utils.AbsCommon;

public abstract class AbsBasePage extends AbsCommon {

    private String baseUrl = System.getProperty("main.page");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void open(String path) {
        path = !path.startsWith("/") ? "/" + path: path;
        driver.get(baseUrl + path);
    }
}
