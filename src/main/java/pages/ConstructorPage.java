package pages;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By createBurgerText = By.xpath("//h1[text()='Соберите бургер']");
    private static final By bunButton = By.xpath("//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    private static final By sauceButton = By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    private static final By fillingsButton = By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку Войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    @Step("Ожидание загрузки страницы конструктора")
    public void waitForConstructorPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerText));
    }

    @Step("Проверка, что страница с конструктором загрузилась")
    public void checkConstructorPageAvailable() {
        waitForConstructorPageLoad();
        Assert.assertTrue(driver.findElement(createBurgerText).isDisplayed());
    }

    @Step("Ожидание загрузки страницы Булки")
    public void waitBunLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunButton, "class", "current"));
    }

    @Step("Ожидание загрузки страницы Соусы")
    public void waitSauceLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(sauceButton, "class", "current"));
    }

    @Step("Ожидание загрузки страницы Начинки")
    public void waitFillingsLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(fillingsButton, "class", "current"));
    }

    @Step("Нажимаем на кнопку Булки")
    public void clickBunButton() { driver.findElement(bunButton).click(); }

    @Step("Нажимаем на кнопку Соусы")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажимаем на кнопку Начинки")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка кнопка Булки")
    public void checkBunButtonIsActive() {
        waitBunLoad();
        String classAttr = driver.findElement(bunButton).getDomAttribute("class");
        Assert.assertTrue(classAttr.contains("current"));
    }

    @Step("Проверка кнопки Соусы")
    public void checkSauceButtonIsActive() {
        waitSauceLoad();
        String classAttr = driver.findElement(sauceButton).getDomAttribute("class");
        Assert.assertTrue(classAttr.contains("current"));
    }

    @Step("Проверка кнопки Начинки")
    public void checkFillingsButtonIsActive() {
        waitFillingsLoad();
        String classAttr = driver.findElement(fillingsButton).getDomAttribute("class");
        Assert.assertTrue(classAttr.contains("current"));
    }
}