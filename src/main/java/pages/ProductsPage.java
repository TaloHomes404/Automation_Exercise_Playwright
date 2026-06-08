package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
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

    public Locator searchedItems(){
        return page.locator(".productinfo p");
    }

    public Locator getProduct(String productName){
        return page.locator(".productinfo p")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }


    public void openViewProductPage(int productId) { page.navigate(ConfigManager.BASE_URL + "product_details/" + productId); }

    public void setQuantityAndAddToCart(int quantity){
        Locator quantityBox = page.locator("input#quantity");
        quantityBox.clear();
        quantityBox.fill(String.valueOf(quantity));
        page.getByText("Add to cart").click();
    }

    public void open() { page.navigate(ConfigManager.BASE_URL + "products"); }

    public void addProductToCart(int productId){
        Locator productColumn = page.locator("div.productinfo").first();
        productColumn.locator("a[data-product-id='" + productId + "']").click();
    }

}
