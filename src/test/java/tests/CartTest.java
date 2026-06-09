package tests;

import base.BaseTest;
import components.Topbar;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;
import pages.ProductsPage;
import pages.ViewCartPage;
import utils.PopupUtils;
import utils.TestResultWatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultWatcher.class)
public class CartTest extends BaseTest {

    @BeforeEach
    void navigateToProductsPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
    }

    @DisplayName("TC-CART01 - Dodanie dwóch produktów do koszyka oraz walidacja poprawności wyświetlania i obliczania zawartości")
    @Feature("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldAddTwoProductsToCartSuccessfully(){
        final int BLUE_TOP_ID = 1;
        final int MEN_TSHIRT_ID = 2;
        productsPage.addProductToCart(BLUE_TOP_ID);
        popupUtils.clickContinueShoppingFromModal();
        productsPage.addProductToCart(MEN_TSHIRT_ID);
        popupUtils.clickViewCartFromModal();

        // Assert

        // Product presence
        assertTrue(viewCartPage.isProductInCart(BLUE_TOP_ID));
        assertTrue(viewCartPage.isProductInCart(MEN_TSHIRT_ID));
        assertEquals(2, viewCartPage.countItemsInCart());

        // Quantities
        assertEquals(1, viewCartPage.getQuantityOfProduct(BLUE_TOP_ID));
        assertEquals(1, viewCartPage.getQuantityOfProduct(MEN_TSHIRT_ID));

        // Prices
        assertEquals(500, viewCartPage.getPriceOfProduct(BLUE_TOP_ID));
        assertEquals(400, viewCartPage.getPriceOfProduct(MEN_TSHIRT_ID));
    }

    @DisplayName("TC-CART02 - Dodawanie kilku sztuk produktu do koszyka i walidacja poprawności wyświetlania ilości i obliczania cen")
    @Feature("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldSuccessfullyAddFewPiecesOfProductInCart(){
        final int SLEEVELESS_DRESS = 3;
        productsPage.openViewProductPage(SLEEVELESS_DRESS);
        productsPage.setQuantityAndAddToCart(4);
        popupUtils.clickViewCartFromModal();

        // Product presence
        assertTrue(viewCartPage.isProductInCart(SLEEVELESS_DRESS));

        // Quantity
        assertEquals(4, viewCartPage.getQuantityOfProduct(SLEEVELESS_DRESS));

        // Price
        assertEquals(4000, viewCartPage.getTotalCostOfProduct(SLEEVELESS_DRESS));
    }

}
