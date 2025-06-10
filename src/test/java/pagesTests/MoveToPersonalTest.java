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
import pages.PersonalPage;

import static data.Endpoints.BASE_URL;

public class MoveToPersonalTest {
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
    }

    @Test
    @DisplayName("Переход на страницу личного кабинета")
    @Description("Переход на страницу личного кабинета по клику на «Личный кабинет»")
    public void moveToPersonalTest() {
        Header header = new Header(driver);
        header.clickAccountButton();

        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.checkAccountPageAvailable();
    }

    @After
    public void tearDown() {
        if (user != null) {
            userApi.deleteUserByCredentials(user.getEmail(), user.getPassword());
        }
        driver.quit();
    }
}
