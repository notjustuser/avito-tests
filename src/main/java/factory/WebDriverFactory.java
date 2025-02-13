package factory;

import data.BrowserNameData;
import exceptions.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private BrowserNameData browserNameData = BrowserNameData.valueOf(System.getProperty("browser").toUpperCase());

    public WebDriver create() {
        switch (browserNameData) {
            case CHROME: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                return new ChromeDriver(options);
            }
            default: {
                throw new BrowserNotSupportedException(browserNameData);
            }
        }
    }
}
