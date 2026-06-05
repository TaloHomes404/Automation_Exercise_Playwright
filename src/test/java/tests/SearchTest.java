package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import components.Topbar;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductsPage;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchTest extends BaseTest {

    @Test
    public void shouldSuccessfullySearchForExistingProduct(){
        Locator searchedItem = page.locator(".productinfo p").filter(new Locator.FilterOptions().setHasText("Rose Pink Embroidered Maxi Dress"));

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
        productsPage.searchForProduct("Rose");
        assertThat(searchedItem).isVisible();
    }

    @Test
    public void shouldFailSearchingForNonexistingProduct(){
        Locator searchedItem = page.locator(".productinfo p");

        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
        productsPage.searchForProduct("Klapa gaźnika matiz 0.8 e");
        assertThat(searchedItem).hasCount(0);

    }

}
