package pages;

import data.User;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private final By inputEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By inputPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By recoverPasswordButton = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Проверка, что открылась страница Вход")
    public void checkForLoadLoginPage() {
        waitForLoadLoginPage();
        Assert.assertTrue(driver.findElement(loginButton).isDisplayed());
    }

    @Step ("Заполняем поле Email")
    public void inputEmail(String newEmail) {
        driver.findElement(inputEmail).sendKeys(newEmail);
    }

    @Step ("Заполняем поле Password")
    public void inputPassword(String newPassword) {
        driver.findElement(inputPassword).sendKeys(newPassword);
    }

    @Step ("Нажимаем кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step ("Нажимаем кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step ("Нажимаем кнопку Восстановить пароль")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step ("Заполняем поля email, пароль и нажимаем кнопку Войти")
    public void login(User user) {
        waitForLoadLoginPage();
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickLoginButton();
    }
}
