package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import testdata.UserData;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @BeforeEach
    void navigateToLoginAndRegisterPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
    }

    @Test
    public void shouldLoginUserSuccessfully(){
        loginPage.login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);

        assertThat(topbar.loggedUserLabel()).containsText(UserData.FULL_NAME);
    }

    @Test
    public void shouldDisplayErrorMessageWhenUserEntersInvalidCredentials(){
        loginPage.login(UserData.INVALID_EMAIL, UserData.INVALID_PASSWORD);

        assertThat(loginPage.incorrectCredentialsError()).isVisible();
    }
}
