package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
import factory.UserFactory;
import models.User;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import testdata.UserData;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegisterTest extends BaseTest {

    @BeforeEach
    void navigateToLoginAndRegisterPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
    }

    @Test
    public void shouldRegisterNewUserSuccessfully(){
        User user = UserFactory.createRandomUser();
        Locator successMessage = signupPage.getAccountCreatedText();
        loginPage.preRegisterAndClickSignupButton(user.getFirstName(), user.getEmail());
        signupPage.completeUserRegistration(user);

        assertThat(successMessage).isVisible();
        assertThat(successMessage).containsText("Account Created!");
    }


    @Test
    public void shouldNotRegisterUserEnteringExistingEmail(){
        loginPage.preRegisterAndClickSignupButton(UserData.FULL_NAME, UserData.VALID_EMAIL);

        assertThat(page.getByText("Email Address already exist!")).isVisible();
    }

}
