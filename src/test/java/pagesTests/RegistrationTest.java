package pagesTests;

import data.User;
import data.WebDriverCreator;
import data.userApi.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static data.Endpoints.*;

public class RegistrationTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver("chrome");
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Регистрация через кнопку Войти в аккаунт")
    @Description("Тест проверяет регистрацию через кнопку Войти в аккаунт")
    public void registrationTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        this.user = registrationPage.registerUser();

        loginPage.login(user);
        constructorPage.checkConstructorPageAvailable();
    }

    @Test
    @DisplayName("Ошибка при регистрации")
    @Description("Тест проверяет наличие ошибки при регистрации, при вводе пароля меньше 6 символов")
    public void passwordErrorTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUserWithShortPassword();
        registrationPage.checkErrorTextVisible();
    }


    @After
    public void tearDown() {
        if (user != null) {
            userApi.deleteUserByCredentials(user.getEmail(), user.getPassword());
        }
        driver.quit();
    }
}
