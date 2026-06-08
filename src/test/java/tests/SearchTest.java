package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import components.Topbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductsPage;
import utils.PopupUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchTest extends BaseTest {

    @BeforeEach
    void navigateToProductsPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
    }

    @Test
    public void shouldSuccessfullySearchForExistingProduct() {
        productsPage.searchForProduct("Rose");

        assertThat(productsPage.getProduct("Rose Pink Embroidered Maxi Dress")).isVisible();
    }

    @Test
    public void shouldDisplayEmptyResultsSearchingForNonExistingProduct() {
        productsPage.searchForProduct("Klapa gaźnika matiz 0.8 e");

        assertThat(productsPage.searchedItems()).hasCount(0);
    }

}
