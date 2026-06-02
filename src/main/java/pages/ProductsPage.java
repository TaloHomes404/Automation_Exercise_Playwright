package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import config.ConfigManager;

public class ProductsPage {

    private final Page page;

    private final Locator searchProductField;
    private final Locator submitSearchButton;

    public ProductsPage(Page page) {
        this.page = page;

        this.searchProductField = page.locator("#search_product");
        this.submitSearchButton = page.locator("#submit_search");
    }

    public void searchForProduct(String name){
        searchProductField.fill(name);
        submitSearchButton.click();
    }

    public void openViewProductPage(String productId) { page.navigate(ConfigManager.BASE_URL + "product_details/" + productId); }

    public void open() { page.navigate(ConfigManager.BASE_URL + "products"); }

}
