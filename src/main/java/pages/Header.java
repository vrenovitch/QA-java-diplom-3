package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;

    private static final By accountButton = By.xpath("//p[text()='Личный Кабинет']");
    private static final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private static final By constructorButton = By.xpath("//p[text()='Конструктор']");


    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку Личный кабинет")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Нажимаем на кнопку Логотипа")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    @Step("Нажимаем на кнопку Конструктора")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
}
