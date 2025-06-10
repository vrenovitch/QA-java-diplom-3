package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {
    private WebDriver driver;

    private static final By loginButton = By.xpath("//a[text()='Войти']");

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем кнопку Войти")
    public void clickLoginButton() { driver.findElement(loginButton).click(); }
}
