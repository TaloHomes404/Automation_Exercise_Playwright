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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import testdata.CardDetailsData;
import testdata.UserData;
import utils.PopupUtils;
import utils.TestResultWatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultWatcher.class)
public class CheckoutTest extends BaseTest {

    @DisplayName("TC-CHECKOUT01 - Checkout zamówionych produktów jako poprawnie zalogowany użytkownik")
    @Feature("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loggedUserShouldSuccessfullyOrderProducts(){
        final int SLEEVELESS_DRESS = 3;

        loginPage.open();
        loginPage.login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();
        checkoutPage.clickPlaceOrder();
        paymentPage.enterCardDetails(CardDetailsData.NAME_ON_CARD, CardDetailsData.CARD_NUMBER, CardDetailsData.CVC_NUMBER, CardDetailsData.EXPIRATION_DATE_MONTH, CardDetailsData.EXPIRATION_DATE_YEAR);
        paymentPage.confirmOrder();
        assertThat(checkoutPage.getOrderConfirmationText()).isVisible();
    }

    @DisplayName("TC-CHECKOUT02 - Checkout zamówionych produktów jako niezalogowany użytkownik")
    @Feature("Checkout")
    @Severity(SeverityLevel.NORMAL)
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

    @DisplayName("TC-CHECKOUT03 - Checkout zamówionych produktów jako nowo zarejestrowany użytkownik")
    @Feature("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkoutAsNewRegisteredUserCorrectlyPlaceOrder(){
        final int SLEEVELESS_DRESS = 3;
        User user = UserFactory.createRandomUser();

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        homePage.addProductToCart(SLEEVELESS_DRESS);
        popupUtils.clickViewCartFromModal();
        viewCartPage.proceedToCheckout();
        popupUtils.clickRegisterOrLoginAccountFromModal();
        loginPage.preRegisterAndClickSignupButton(user.getFirstName(), user.getEmail());
        signupPage.completeUserRegistration(user);
        topbar.clickCart();
        viewCartPage.proceedToCheckout();
        assertTrue(viewCartPage.isProductInCart(SLEEVELESS_DRESS));
        assertThat(checkoutPage.getDeliveryAddress()).containsText(user.getFirstName() + " " + user.getLastName() );
        assertThat(checkoutPage.getDeliveryAddress()).containsText(user.getAddress());
    }
}
