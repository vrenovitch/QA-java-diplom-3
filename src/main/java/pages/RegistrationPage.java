package pages;

import data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class RegistrationPage {
    private WebDriver driver;


    private static final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");;
    private static final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By loginButton = By.xpath("//a[text()='Войти']");
    private static final By errorText = By.className("input__error");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Нажимаем на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажимаем на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполняем имя")
    public void inputName(String value) {
        driver.findElement(nameField).sendKeys(value);
    }

    @Step("Заполняем пароль")
    public void inputPassword(String value) {
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Заполняем email")
    public void inputEmail(String value) {
        driver.findElement(emailField).sendKeys(value);
    }

    @Step("Заполняем поля name, password, email и нажимаем на кнопку Зарегистрироваться")
    public User registerUser() {
        User user = User.generateRandomUser();

        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegisterButton();

        return user;
    }
    @Step("Заполняем поля name, password, email и нажимаем на кнопку Зарегистрироваться (с коротким паролем)")
    public User registerUserWithShortPassword() {
        User user = User.generateRandomUser();

        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword("123");
        clickRegisterButton();

        return user;
    }

    @Step("Проверяем, что текст ошибки появился")
    public void checkErrorTextVisible() {
        assertTrue(driver.findElement(RegistrationPage.errorText).isDisplayed());
    }
}
