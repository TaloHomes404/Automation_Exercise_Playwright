package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;
import pages.LoginPage;
import testdata.UserData;
import utils.PopupUtils;
import utils.TestResultWatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(TestResultWatcher.class)
public class LoginTest extends BaseTest {

    @BeforeEach
    void navigateToLoginAndRegisterPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
    }

    @DisplayName("TC-LOG01 - Zaloguj użytkownika podając istniejące i poprawne dane")
    @Feature("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldLoginUserSuccessfully(){
        loginPage.login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);

        assertThat(topbar.loggedUserLabel()).containsText(UserData.FULL_NAME);
    }

    @DisplayName("TC-LOG02 - Zaloguj użytkownika podając niepoprawne dane")
    @Feature("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldDisplayErrorMessageWhenUserEntersInvalidCredentials(){
        loginPage.login(UserData.INVALID_EMAIL, UserData.INVALID_PASSWORD);

        assertThat(loginPage.incorrectCredentialsError()).isVisible();
    }
}
