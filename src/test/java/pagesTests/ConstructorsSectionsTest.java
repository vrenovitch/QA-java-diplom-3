package pagesTests;

import data.WebDriverCreator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;

import static data.Endpoints.BASE_URL;

public class ConstructorsSectionsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver("chrome");
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Перехода к разделу Булки")
    @Description("Проверка выполнения перехода к разделу Булки")
    public void bunSectionTest() {
        ConstructorPage constructorsPage = new ConstructorPage(driver);
        constructorsPage.checkBunButtonIsActive();
    }

    @Test
    @DisplayName("Перехода к разделу Соусы")
    @Description("Проверка выполнения перехода к разделу Соусы")
    public void sousesSectionTest() {
        ConstructorPage constructorsPage = new ConstructorPage(driver);
        constructorsPage.clickSauceButton();
        constructorsPage.checkSauceButtonIsActive();
    }

    @Test
    @DisplayName("Перехода к разделу Начинки")
    @Description("Проверка выполнения перехода к разделу Начинки")
    public void fillingsSectionTest() {
        ConstructorPage constructorsPage = new ConstructorPage(driver);
        constructorsPage.clickFillingsButton();
        constructorsPage.checkFillingsButtonIsActive();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
