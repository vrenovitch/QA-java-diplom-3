package pagesTests;

import data.User;
import data.WebDriverCreator;
import data.userApi.CreateUser;
import data.userApi.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static data.Endpoints.BASE_URL;

public class LoginTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        User randomUser = User.generateRandomUser();
        userApi = new UserApi();
        CreateUser apiUser = new CreateUser(randomUser.getEmail(), randomUser.getPassword(), randomUser.getName());
        userApi.createUser(apiUser);
        this.user = randomUser;
        driver = WebDriverCreator.createWebDriver("chrome");
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт")
    @Description("Авторизация через кнопку Войти в аккаунт на главной странице")
    public void loginTestConstructor() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        constructorPage.checkConstructorPageAvailable();
    }

    @Test
    @DisplayName("Авторизация через кнопку Личный кабинет")
    @Description("Авторизация через кнопку Личный кабинет в хедере")
    public void loginTestAccountButton() {
        Header header = new Header(driver);
        header.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.checkConstructorPageAvailable();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    @Description("Авторизация через кнопку Войти в форме регистрации")
    public void loginTestRegistrationButton() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();

        loginPage.login(user);

        constructorPage.checkConstructorPageAvailable();
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    @Description("Авторизация через кнопку Войти в форме восстановления пароля")
    public void loginTestRestorePasswordButton() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordButton();

        RecoveryPage recoveryPage = new RecoveryPage(driver);
        recoveryPage.clickLoginButton();

        loginPage.login(user);

        constructorPage.checkConstructorPageAvailable();
    }


    @After
    public void tearDown() {
        if (user != null) {
            userApi.deleteUserByCredentials(user.getEmail(), user.getPassword());
        }
        driver.quit();
    }
}
