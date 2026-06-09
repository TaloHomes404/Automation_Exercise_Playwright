package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
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
import utils.PopupUtils;
import utils.TestResultWatcher;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(TestResultWatcher.class)
public class SearchTest extends BaseTest {

    @BeforeEach
    void navigateToProductsPage() {
        homePage.open();
        popupUtils.acceptCookiesIfPopupIsVisible();
        topbar.clickProducts();
    }

    @DisplayName("TC-SEARCH01 - Wyszukiwanie prawidłowego produktu")
    @Feature("Search")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldSuccessfullySearchForExistingProduct() {
        productsPage.searchForProduct("Rose");

        assertThat(productsPage.getProduct("Rose Pink Embroidered Maxi Dress")).isVisible();
    }

    @DisplayName("TC-SEARCH01 - Wyszukiwanie nieistniejącego produktu")
    @Feature("Search")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldDisplayEmptyResultsSearchingForNonExistingProduct() {
        productsPage.searchForProduct("Klapa gaźnika matiz 0.8 e");

        assertThat(productsPage.searchedItems()).hasCount(0);
    }
}
