package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void shouldLoginUserSuccessfully(){
        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);
        Topbar topbar = new Topbar(page);
        PopupUtils popupUtils = new PopupUtils(page);
        Locator loggedUser = page.getByText("Logged in as");

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.login("robertsmith11@email.com", "robertsmithpass123");
        assertThat(loggedUser).containsText("Robert Smith");
    }

    @Test
    public void shouldNotLoginUserEnteringWrongCredentials(){
        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);
        Topbar topbar = new Topbar(page);
        PopupUtils popupUtils = new PopupUtils(page);
        Locator incorrectCredentialsError = page.getByText("Your email or password is incorrect!");

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.login("testingwrong192@email.com", "wrongpass15z5z2z");
        assertThat(incorrectCredentialsError).isVisible();
    }


}
