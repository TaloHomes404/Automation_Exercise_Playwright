package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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


}
