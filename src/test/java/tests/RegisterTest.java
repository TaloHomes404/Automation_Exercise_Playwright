package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
import factory.UserFactory;
import models.User;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegisterTest extends BaseTest {

    @Test
    public void shouldRegisterNewUserSuccessfully(){

        Locator accountCreated = page.locator("[data-qa='account-created']");
        Faker faker = new Faker();
        PopupUtils popupUtils = new PopupUtils(page);
        User user = UserFactory.createRandomUser();
        Gender gender = faker.options().option(Gender.class);
        int day = faker.number().numberBetween(1,32);
        int month = faker.number().numberBetween(1,13);
        int year = faker.number().numberBetween(1990,2005);


        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.preRegisterAndClickSignupButton(user.getFirstName(), user.getEmail());
        signupPage.selectGender(gender);
        signupPage.enterPassword(user.getPassword());
        signupPage.enterDateOfBirth(day,month,year);
        signupPage.signupForNewsletter();
        signupPage.signupForSpecialOffers();
        signupPage.enterAddressInformations(user.getFirstName(), user.getLastName(), user.getAddress());
        signupPage.selectCountry("United States");
        signupPage.enterAddressDetails(user.getState(), user.getCity(), user.getZipcode());
        signupPage.enterPhoneNumber(user.getPhoneNumber());
        signupPage.clickCreateAccount();

        assertThat(accountCreated).isVisible();
        assertThat(accountCreated).containsText("Account Created!");
    }


    @Test
    public void shouldNotRegisterUserEnteringExistingEmail(){
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.preRegisterAndClickSignupButton("Robert", "robertsmith11@email.com");
        assertThat(page.getByText("Email Address already exist!")).isVisible();
    }

}
