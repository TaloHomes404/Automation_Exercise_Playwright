package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
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

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);
        Topbar topbar = new Topbar(page);
        Locator accountCreated = page.locator("[data-qa='account-created']");
        Faker faker = new Faker();
        PopupUtils popupUtils = new PopupUtils(page);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().fullAddress();
        String email = faker.internet().emailAddress();
        String password = faker.credentials().password();
        Gender gender = faker.options().option(Gender.class);
        int day = faker.number().numberBetween(1,32);
        int month = faker.number().numberBetween(1,13);
        int year = faker.number().numberBetween(1990,2005);
        String state = faker.address().state();
        String city = faker.address().city();
        String zipcode = faker.address().zipCode();
        String phoneNumber = faker.phoneNumber().phoneNumber();


        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.preRegisterAndClickSignupButton(firstName, email);
        signupPage.selectGender(gender);
        signupPage.enterPassword(password);
        signupPage.enterDateOfBirth(day,month,year);
        signupPage.signupForNewsletter();
        signupPage.signupForSpecialOffers();
        signupPage.enterAddressInformations(firstName, lastName, address);
        signupPage.selectCountry("United States");
        signupPage.enterAddressDetails(state, city, zipcode);
        signupPage.enterPhoneNumber(phoneNumber);
        signupPage.clickCreateAccount();

        assertThat(accountCreated).isVisible();
        assertThat(accountCreated).containsText("Account Created!");
    }


    @Test
    public void shouldNotRegisterUserEnteringExistingEmail(){
        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);
        Topbar topbar = new Topbar(page);
        PopupUtils popupUtils = new PopupUtils(page);

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickLogin();
        loginPage.preRegisterAndClickSignupButton("Robert", "robertsmith11@email.com");
        assertThat(page.getByText("Email Address already exist!")).isVisible();
    }

}
