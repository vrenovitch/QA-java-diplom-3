package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PersonalPage {
    private WebDriver driver;

    private static final By profileText = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private static final By logoutButton = By.xpath("//button[text()='Выход']");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку Выход")
    public void clickLogoutButton() {
        waitForAccountPageLoad();
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидание загрузки страницы")
    public void waitForAccountPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileText));
    }

    @Step("Проверка, что открылась страница с данными профиля")
    public void checkAccountPageAvailable() {
        waitForAccountPageLoad();
        Assert.assertTrue(driver.findElement(profileText).isDisplayed());
    }
}
