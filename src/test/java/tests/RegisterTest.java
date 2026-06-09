package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
import factory.UserFactory;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.User;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import testdata.UserData;
import utils.PopupUtils;
import utils.TestResultWatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(TestResultWatcher.class)
public class RegisterTest extends BaseTest {

    @BeforeEach
    void navigateToLoginAndRegisterPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
    }

    @DisplayName("TC-REG01 - Poprawna rejestracja użytkownika w systemie")
    @Feature("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldRegisterNewUserSuccessfully(){
        User user = UserFactory.createRandomUser();
        Locator successMessage = signupPage.getAccountCreatedText();
        loginPage.preRegisterAndClickSignupButton(user.getFirstName(), user.getEmail());
        signupPage.completeUserRegistration(user);

        assertThat(successMessage).isVisible();
        assertThat(successMessage).containsText("Account Created!");
    }

    @DisplayName("TC-REG02 - Rejestrowanie użytkownika z już istniejącymi w systemie danymi")
    @Feature("Register")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldNotRegisterUserEnteringExistingEmail(){
        loginPage.preRegisterAndClickSignupButton(UserData.FULL_NAME, UserData.VALID_EMAIL);

        assertThat(page.getByText("Email Address already exist!")).isVisible();
    }

}
