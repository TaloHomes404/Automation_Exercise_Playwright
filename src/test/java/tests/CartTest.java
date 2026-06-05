package tests;

import base.BaseTest;
import components.Topbar;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.ViewCartPage;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void shouldAddTwoProductsToCartSuccessfully(){

        ViewCartPage viewCartPage = new ViewCartPage(page);
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);
        Topbar topbar = new Topbar(page);
        PopupUtils popupUtils = new PopupUtils(page);

        final int BLUE_TOP_ID = 1;
        final int MEN_TSHIRT_ID = 2;

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
        popupUtils.closeAdPopup();
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

    @Test
    public void shouldSuccessfullyAddFewPiecesOfProductInCart(){

        ViewCartPage viewCartPage = new ViewCartPage(page);
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);
        Topbar topbar = new Topbar(page);
        PopupUtils popupUtils = new PopupUtils(page);

        final int SLEEVELESS_DRESS = 3;

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
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
