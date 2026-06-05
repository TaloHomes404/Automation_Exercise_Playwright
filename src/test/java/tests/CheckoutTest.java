package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    public void loggedUserShouldSuccessfullyOrderProducts(){

        HomePage homePage = new HomePage(page);
        LoginPage loginPage = new LoginPage(page);
        ViewCartPage viewCartPage = new ViewCartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);
        PaymentPage paymentPage = new PaymentPage(page);
        PopupUtils popupUtils = new PopupUtils(page);
        final int SLEEVELESS_DRESS = 3;
        Locator orderPlacedConfirmation = page.locator("[data-qa='order-placed']");

        loginPage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        loginPage.login("robertsmith11@email.com", "robertsmithpass123");
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();
        checkoutPage.clickPlaceOrder();
        paymentPage.enterCardDetails("Robert Smith", "5513 3110 0011 1005", "777", "06", "2036");
        paymentPage.confirmOrder();
        assertThat(orderPlacedConfirmation).isVisible();
    }

    @Test
    public void placeOrderAsGuestDisplayCorrectMessage(){
        HomePage homePage = new HomePage(page);
        PopupUtils popupUtils = new PopupUtils(page);
        ViewCartPage viewCartPage = new ViewCartPage(page);
        final int SLEEVELESS_DRESS = 3;
        Locator cartModal = page.locator("div.modal-content");

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();

        assertThat(cartModal).isVisible();
        assertThat(cartModal).containsText("Register / Login");
    }

    @Test
    public void checkoutAsNewRegisteredUserCorrectlyPlaceOrder(){
        HomePage homePage = new HomePage(page);
        PopupUtils popupUtils = new PopupUtils(page);
        LoginPage loginPage = new LoginPage(page);
        ViewCartPage viewCartPage = new ViewCartPage(page);
        SignupPage signupPage = new SignupPage(page);
        Topbar topbar = new Topbar(page);
        Faker faker = new Faker();
        final int SLEEVELESS_DRESS = 3;
        Locator addressBox = page.locator("#address_delivery");
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
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();
        popupUtils.clickRegisterOrLoginAccountFromModal();
        loginPage.preRegisterAndClickSignupButton(firstName, email);
        signupPage.selectGender(gender);
        signupPage.enterPassword(password);
        signupPage.enterDateOfBirth(day,month,year);
        signupPage.enterAddressInformations(firstName, lastName, address);
        signupPage.selectCountry("United States");
        signupPage.enterAddressDetails(state, city, zipcode);
        signupPage.enterPhoneNumber(phoneNumber);
        signupPage.clickCreateAccount();
        topbar.clickCart();
        viewCartPage.proceedToCheckout();
        assertTrue(viewCartPage.isProductInCart(SLEEVELESS_DRESS));
        assertThat(addressBox).containsText(firstName + " " + lastName );
        assertThat(addressBox).containsText(address);
    }


}
