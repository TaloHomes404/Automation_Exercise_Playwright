package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import components.Topbar;
import enums.Gender;
import factory.UserFactory;
import models.User;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    public void loggedUserShouldSuccessfullyOrderProducts(){

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
        Faker faker = new Faker();
        final int SLEEVELESS_DRESS = 3;
        Locator addressBox = page.locator("#address_delivery");
        User user = UserFactory.createRandomUser();
        Gender gender = faker.options().option(Gender.class);
        int day = faker.number().numberBetween(1,32);
        int month = faker.number().numberBetween(1,13);
        int year = faker.number().numberBetween(1990,2005);




        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();
        popupUtils.clickRegisterOrLoginAccountFromModal();
        loginPage.preRegisterAndClickSignupButton(user.getFirstName(), user.getEmail());
        signupPage.selectGender(gender);
        signupPage.enterPassword(user.getPassword());
        signupPage.enterDateOfBirth(day,month,year);
        signupPage.enterAddressInformations(user.getFirstName(), user.getLastName(), user.getAddress());
        signupPage.selectCountry("United States");
        signupPage.enterAddressDetails(user.getState(), user.getCity(), user.getZipcode());
        signupPage.enterPhoneNumber(user.getPhoneNumber());
        signupPage.clickCreateAccount();
        topbar.clickCart();
        viewCartPage.proceedToCheckout();
        assertTrue(viewCartPage.isProductInCart(SLEEVELESS_DRESS));
        assertThat(addressBox).containsText(user.getFirstName() + " " + user.getLastName() );
        assertThat(addressBox).containsText(user.getAddress());
    }


}
