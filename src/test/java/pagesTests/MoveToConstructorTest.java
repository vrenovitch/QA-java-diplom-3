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
import pages.ConstructorPage;
import pages.Header;
import pages.LoginPage;

import static data.Endpoints.BASE_URL;

public class MoveToConstructorTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        User randomUser = User.generateRandomUser();
        this.user = randomUser;

        userApi = new UserApi();

        CreateUser apiUser = new CreateUser(user.getEmail(), user.getPassword(), user.getName());
        userApi.createUser(apiUser);

        driver = WebDriverCreator.createWebDriver("chrome");
        driver.get(BASE_URL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        Header header = new Header(driver);
        header.clickAccountButton();
    }

    @Test
    @DisplayName("Переход на страницу Конструктора")
    @Description("Переход на страницу Конструктора через кнопку на Конструктор")
    public void moveToConstructorTestWithButtonConstructor() {
        Header header = new Header(driver);
        header.clickConstructorButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.checkConstructorPageAvailable();
    }

    @Test
    @DisplayName("Переход на страницу Конструктора")
    @Description("Переход на страницу Конструктора через логотип")
    public void moveToConstructorTestWithLogoConstructor() {
        Header header = new Header(driver);
        header.clickLogo();

        ConstructorPage constructorPage = new ConstructorPage(driver);
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
