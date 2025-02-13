package pages;


import components.popups.CreateAdPopup;
import data.AdContent;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends AbsBasePage {

    //селекторы для СreateAdTest
    private final By createButtonLocator = By.xpath("//button[text()='Создать']");
    private final By saveButtonSelector = By.cssSelector("button[type='submit']");
    private final String inputSelectorTemplate = "input[name='%s']";

    //селекторы для EditAdTest
    private final By adSelector = By.cssSelector(".css-1cickmn:first-of-type a");
    private final By editButtonSelector = By.cssSelector("svg[height='40']");
    private final By nameHeaderSelector = By.cssSelector("[class='chakra-heading css-1mr9o9q']");

    //ceлекторы для поиска
    private final By searchSelector = By.cssSelector("input[placeholder='Поиск по объявлениям']");
    private final By searchButtonLocator = By.xpath("//button[text()='Найти']");

    //селекторы для SearchAdTest
    private final By limitSelector = By.id("menu-button-:r5:");
    private final By limit25Selector = By.id("menu-list-:r5:-menuitem-:ra:");
    private final By adListSelector = By.cssSelector("div.css-1cickmn");
    private final By titleSelector = By.cssSelector("h4.css-rkqtls");
    private final By nextPageButtonSelector = By.xpath("//button[text()='Следующая']");


    //селекторы для проверки создания объявления
    private final By headingSelector = By.cssSelector("h2[class*='heading']");
    private final By priceSelector = By.cssSelector("p[class='chakra-text css-r1bsln']");
    private final By descriptionSelector = By.cssSelector("p[class='chakra-text css-i3jkqk']");
    private final By imageSelector = By.cssSelector("img[alt='product image']");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public CreateAdPopup clickCreateButton() {
        click(createButtonLocator);
        return new CreateAdPopup(driver);
    }

    public MainPage input(String name, String input) {
        WebElement field = driver.findElement(By.cssSelector(String.format(inputSelectorTemplate, name)));
        field.clear();
        field.sendKeys(input);
        return this;
    }

    public MainPage check(By by, String exp) {
//        WebElement field = driver.findElement(by);
        Assertions.assertEquals(exp, waiter.getElement(by).getText());
        return this;
    }

    public MainPage fullInput(AdContent adContent) {
        input("name", adContent.getName());
        input("price", adContent.getPrice());
        input("description", adContent.getDescription());
        input("imageUrl", adContent.getImageUrl());

        return this;
    }

    public MainPage fullCheck(AdContent adContent) {
        check(headingSelector, adContent.getName());
        check(priceSelector, adContent.getPrice());
        check(descriptionSelector, adContent.getDescription());
        Assertions.assertEquals(adContent.getImageUrl(), waiter.getElement(imageSelector).getAttribute("currentSrc"));

        return this;
    }

    public MainPage click(By selector) {
        waiter.getElement(selector).click();
        return new MainPage(driver);
    }

    public MainPage clickSaveButton() {
        click(saveButtonSelector);
        return new MainPage(driver);
    }

    public MainPage clickFirstAd() {
        click(adSelector);
        return new MainPage(driver);
    }

    public MainPage clickEditButton() {
        click(editButtonSelector);
        return new MainPage(driver);
    }

    public MainPage checkName(String exp) {
        driver.navigate().refresh();
        Assertions.assertEquals(exp, waiter.getElement(nameHeaderSelector).getText());
        return new MainPage(driver);
    }

    public MainPage search(String input) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> oldListings = driver.findElements(adListSelector);
        WebElement field = waiter.getElement(searchSelector);
        field.click();
        field.sendKeys(input);
        waiter.getElement(searchButtonLocator).click();
        wait.until(ExpectedConditions.stalenessOf(oldListings.get(0)));
        return new MainPage(driver);
    }

    public MainPage changeLimit() {
        click(limitSelector);
        click(limit25Selector);
        return new MainPage(driver);
    }

    public MainPage titleCheck(String input) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            List<WebElement> listings = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adListSelector));
            int number = listings.size();

            if (number == 0) {
                throw new AssertionError("Объявления не найдены!");
            }

            for (WebElement listing : listings) {
                WebElement titleElement = listing.findElement(titleSelector);
                String titleText = titleElement.getText().toLowerCase();
                if (!titleText.contains(input)) {
                    throw new AssertionError("Ошибка: в объявлении нет этого слова. Заголовок: " + titleText);
                }
            }
            if (number < 25) {
                break;
            }

            if (number == 25) {
                click(nextPageButtonSelector);
                wait.until(ExpectedConditions.stalenessOf(listings.get(0)));
            }
        }
        return new MainPage(driver);
    }
}